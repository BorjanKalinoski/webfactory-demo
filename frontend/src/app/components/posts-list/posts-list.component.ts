import {Component, OnDestroy, OnInit} from '@angular/core';
import {PostService} from '../../services/post.service';
import {Post} from '../../models/Post';
import {Subscription} from 'rxjs';


@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.css']
})
export class PostsListComponent implements OnInit, OnDestroy {

  posts: Post[];
  private subscriptions: Subscription = new Subscription();
  failed = false;
  error;


  constructor(private postService: PostService) {  }

  ngOnInit(): void {
    this.subscriptions.add(
      this.postService.getPosts().subscribe(posts => {
        this.posts = posts;
      }, (err) => {
        this.failed = true;
        this.error = err.error.error; // Ha
      })
    );

    this.subscriptions.add(
      this.postService.newPostSubject.subscribe(post => {
        this.posts = [post, ...this.posts, ];
      }, (err) => {
        this.failed = true;
        this.error = err.error.error;
      })
    );
    this.subscriptions.add(
      this.postService.deletePostSubject.subscribe(data => {
        this.posts = this.posts.filter(post => post.id !== data.id);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }


}