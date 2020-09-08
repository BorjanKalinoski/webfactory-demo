import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../models/User';
import {environment} from '../environments/environment';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User;

  constructor(private http: HttpClient) {

  }

  register(data: User): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/users/register`, data);
  }

  login(data: User): Observable<User> {
    return this.http.post<User>(`${environment.apiUrl}/users/login`, data);
  }
}
