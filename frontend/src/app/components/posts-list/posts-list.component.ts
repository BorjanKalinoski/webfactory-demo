import { Component, OnInit } from '@angular/core';
import {PostService} from '../../services/post.service';
import {Post} from '../../models/Post';

@Component({
  selector: 'app-posts-list',
  templateUrl: './posts-list.component.html',
  styleUrls: ['./posts-list.component.css']
})
export class PostsListComponent implements OnInit {

  posts: Post[];
  constructor(private postService: PostService) {  }

  async ngOnInit(): void {
    this.posts = await this.postService.getPosts();
    console.log(this.posts);
  }

}
