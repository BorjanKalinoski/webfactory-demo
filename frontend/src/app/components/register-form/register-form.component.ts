import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})

export class RegisterFormComponent implements OnInit {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    fullName: new FormControl('', Validators.required)
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
    console.log(this.loading);
    const formValue = this.form.value;
    console.log(formValue);
    try {
      const data = await this.userService.register(formValue);
      console.log(data);
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

  get fullName() {
    return this.form.get('fullName');
  }

  get password() {
    return this.form.get('password');
  }
}
