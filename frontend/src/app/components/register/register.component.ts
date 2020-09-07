import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    fullName: new FormControl('', Validators.required)
  });
  loading = false;
  success = false;
  failed = false;
  errorMessage = '';

  constructor(private userService: UserService, private router: Router) {

  }

  ngOnInit(): void {
  }

  // tslint:disable-next-line:typedef
  async submitHandler() {
    this.loading = true;
    const formValue = this.form.value;
    try {
      const data = await this.userService.register(formValue);
      this.success = true;
      await this.router.navigate(['/login']);
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
