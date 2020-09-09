import {Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';
import {PostService} from '../../services/post.service';
import {Subscription} from 'rxjs';
import {Post} from '../../models/Post';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  private subscriptions: Subscription = new Subscription();

  constructor(
    private router: Router,
    private userService: UserService,
    private postService: PostService) {
  }

  ngOnInit(): void {
    if (!this.userService.user) {
      this.router.navigate(['/login']);
    }
  }

  isLoggedIn() {
    return this.userService.user !== undefined;
  }

  getPosts() {
    this.subscriptions.add(
      this.postService.getPosts().subscribe(posts => {
        this.postService.getPostsSubject.next(posts);
      })
    );
  }

  getPostsByUserId() {
    if (!this.isLoggedIn()) {
      return;
    }
    this.subscriptions.add(this.postService.getPostsByUserId(this.userService.user.id).subscribe(posts => {
        this.postService.getPostsSubject.next(posts);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
