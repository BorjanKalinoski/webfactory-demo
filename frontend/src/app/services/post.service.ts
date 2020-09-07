import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Post} from '../models/Post';
import {environment} from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  posts: Post[];
  constructor(private http: HttpClient) {}


  getPosts(): Promise<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts`).toPromise();
  }

  getPostsByUserId(userId: string): Promise<Post[]> {
    return this.http.get<Post[]>(`${environment.apiUrl}/posts/user/${userId}`).toPromise();
  }

  getPostById(id: string): Promise<Post> {
    return this.http.get<Post>(`${environment.apiUrl}/posts/${id}`).toPromise();
  }

  submitPost(data: Post): Promise<Post> {
    return this.http.post<Post>(`${environment.apiUrl}/posts`, data).toPromise();
  }

  deletePostById(id: string): Promise<Post> {
    return this.http.delete<Post>(`${environment.apiUrl}/posts/${id}`).toPromise();
  }

}
