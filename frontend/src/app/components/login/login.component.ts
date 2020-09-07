import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });
  loading = false;
  success = false;
  failed = false;
  errorMessage = '';

  constructor(private userService: UserService) {

  }


  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  async submitHandler() {
    this.loading = true;
    const formValue = this.form.value;
    try {
      const data = await this.userService.login(formValue);
      this.success = true;
    }catch ({error}) {
      this.success = false;
      this.failed = true;
      this.errorMessage = error.error;
    }
    this.loading = false;
  }

  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }
}
