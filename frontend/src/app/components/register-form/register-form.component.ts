import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from '@angular/forms';

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

  constructor() {}

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  submitHandler() {
    this.loading = true;
    const formValue = this.form.value;

    try {
      this.success = true;
    }catch (err) {
      console.log(err);
      this.success = false;
    }
    this.loading = false;
  }

  get email() {
    return this.form.get('email');
  }

  // tslint:disable-next-line:typedef
  get fullName() {
    return this.form.get('fullName');
  }

  get password() {
    return this.form.get('password');
  }
}
