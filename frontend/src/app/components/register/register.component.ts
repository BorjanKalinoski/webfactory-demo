import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements  OnDestroy {

  loading = false;
  failed = false;
  errorMessage = '';
  subscriptions: Subscription = new Subscription();

  form = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    fullName: new FormControl('', Validators.required)
  });

  constructor(
    private userService: UserService,
    private router: Router
  ) {}


  submitHandler() {
    this.loading = true;
    this.failed = false;

    const formValue = this.form.value;
    this.subscriptions.add(
      this.userService.register(formValue).subscribe(data => {
        this.loading = false;
        this.router.navigate(['/login']);
      }, ({error}) => {
        this.failed = true;
        this.errorMessage = error.error;
        this.loading = false;
      })
    );

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

  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
}
