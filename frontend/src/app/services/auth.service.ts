import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from '../models/User';
import { Observable, tap } from 'rxjs';
import { HotToastService } from '@ngneat/hot-toast';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly urlEndPoint: string = `${environment.loginUrl}`;
  private router = inject(Router);
  private http = inject(HttpClient);
  private toast = inject(HotToastService);


  constructor() { }

  login(user: User): Observable<User> {
    return this.http.post<User>(`${this.urlEndPoint}/login`, user).pipe(
      tap((res: any) => {
        localStorage.setItem('token', res.token);
        this.router.navigate(['/admin']);
      }),
    );
  }

  /** Load token from localstorage */
  getToken() {
    const token: any = localStorage.getItem('token');
    if (token) {
      return token;
    }
    localStorage.clear();
    return null;
  }
}