import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Post} from '../models/Post';
import {environment} from '../environments/environment';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  posts: Post[];
  newPostSubject: Subject<Post> = new Subject<Post>();
  deletePostSubject: Subject<Post> = new Subject<Post>();

  constructor(private http: HttpClient) {}


  getPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts`);
  }

  getPostsByUserId(userId: string): Observable<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts/user/${userId}`);
  }

  getPostById(id: string): Observable<Post> {
    return this.http.get<Post>(`${environment.apiUrl}/posts/${id}`);
  }

  submitPost(data: Post): Observable<Post> {
    return this.http.post<Post>(`${environment.apiUrl}/posts`, data);
  }

  deletePostById(id: string): Observable<Post> {
    return this.http.delete<Post>(`${environment.apiUrl}/posts/${id}`);
  }

}
