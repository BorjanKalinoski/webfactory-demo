import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import * as moment from 'moment';
import {UserService} from '../../services/user.service';
import {PostService} from '../../services/post.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnDestroy{
  @Input() id;
  @Input() title;
  @Input() description;
  @Input() createdAt;
  @Input() userId;
  private subscriptions: Subscription = new Subscription();

  constructor(
    private userService: UserService,
    private postService: PostService
  ) { }

  getFormat(date) {
    return moment(date).format('DD-MM-YYYY hh:mm A');
  }

  renderX() {
    if (!this.userService.user) {
      return false;
    }
    return this.userService.user.id === this.userId;
  }

  deletePost() {
    this.subscriptions.add(
      this.postService.deletePostById(this.id).subscribe(data => {
        this.postService.deletePostSubject.next(data);
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
