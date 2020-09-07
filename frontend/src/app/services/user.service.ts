import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../models/User';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {

  }

  register(data: User): Promise<User> {
    return this.http.post<User>(`${environment.apiUrl}/users/register`, data).toPromise();
  }

  login(data: User): Promise<User> {
    return this.http.post<User>(`${environment.apiUrl}/users/login`, data).toPromise();
  }
}
