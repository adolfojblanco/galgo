import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { User } from '../models/User';
import { Observable, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { HotToastService } from '@ngxpert/hot-toast';
import { jwtDecode } from "jwt-decode";

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

  /** Confirm and activate account */
  confirmAccount(token: string, username: string): Observable<any> {
    console.log(this.urlEndPoint)
    return this.http.post<any>(`${this.urlEndPoint}/users/confirm-account/${token}`, username);
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

  /** return login user */
  getAuthUser() {
    const token = this.getToken();
    const decoded: any = jwtDecode(token);
    return decoded;
  }

  /** Check the user rol */
  hasRoles(role: string): boolean {
    const token = this.getToken();
    if (token) {
      const decoded: any = jwtDecode(token);
      if (decoded.authorities.includes(role)) {
        return true;
      }
    }
    return false;
  }

    /** Clean local storage and redirect */
    logout() {
      localStorage.clear();
      this.router.navigate(['/auth/login']);
    }
}
