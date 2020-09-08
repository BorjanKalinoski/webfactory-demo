import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validator, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  loading = false;
  failed = false;
  errorMessage = '';
  subscriptions: Subscription = new Subscription();

  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)])
  });

  constructor(private userService: UserService, private router: Router) {
  }


  ngOnInit(): void {
  }

  async submitHandler() {
    this.loading = true;
    this.failed = false;

    const formValue = this.form.value;

    this.subscriptions.add(
      this.userService.login(formValue).subscribe(user => {
        this.userService.user = user;
        this.router.navigate(['/']);
      }, ({error}) => {
        this.failed = true;
        this.errorMessage = error.error;
      })
    );
    this.loading = false;
  }


  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
