import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {PostService} from '../../services/post.service';

@Component({
  selector: 'app-submit-post',
  templateUrl: './submit-post.component.html',
  styleUrls: ['./submit-post.component.css']
})
export class SubmitPostComponent implements OnInit {
  form = new FormGroup({
    title: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required, Validators.minLength(6)])
  });
  loading = false;
  success = false;
  failed = false;
  errorMessage = '';

  constructor(private postService: PostService, private userService: UserService) {

  }


  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  async submitHandler() {
    this.loading = true;
    const formValue = this.form.value;
    formValue.userId = this.userService.user.id;
    try {
      const data = await this.postService.submitPost(formValue);
      this.success = true;
    }catch ({error}) {
      this.success = false;
      this.failed = true;
      this.errorMessage = error.error;
    }
    this.loading = false;
  }

  get title() {
    return this.form.get('title');
  }

  get description() {
    return this.form.get('description');
  }
}
