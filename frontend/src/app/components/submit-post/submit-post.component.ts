import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {PostService} from '../../services/post.service';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';

@Component({
  selector: 'app-submit-post',
  templateUrl: './submit-post.component.html',
  styleUrls: ['./submit-post.component.css']
})
export class SubmitPostComponent implements OnDestroy {

  loading = false;
  failed = false;
  errorMessage = '';
  subscriptions: Subscription = new Subscription();

  form = new FormGroup({
    title: new FormControl('', [Validators.required, Validators.minLength(6)]),
    description: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor(
    private postService: PostService,
    private userService: UserService,
    private router: Router
  ) {}


  async submitHandler() {
    this.loading = true;
    this.failed = false;
    const formValue = this.form.value;
    if (!this.userService.user) {
      await this.router.navigate(['/login']);
    }

    formValue.userId = this.userService.user.id;
    this.subscriptions.add(
      this.postService.submitPost(formValue).subscribe(post => {
        this.loading = false;
        this.postService.newPostSubject.next(post);
      }, ({error}) => {
        this.failed = true;
        this.loading = false;
        this.errorMessage = error.error; // Hah
      })
    );

  }

  get title() {
    return this.form.get('title');
  }

  get description() {
    return this.form.get('description');
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }

}
