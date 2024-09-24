import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { catchError, throwError } from 'rxjs';
import { HotToastService } from '@ngxpert/hot-toast';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const toast = inject(HotToastService);
  const router = inject(Router);
  const authServices = inject(AuthService);

  const token: string | null = authServices.getToken();
  let request = req;
  if (token != undefined) {
    const authReq = req.clone({
      setHeaders: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`,
      },
    });
  }

  return next(req).pipe(
    catchError((err: any) => {
      if (err.status === 401) {
        return throwError(() => {
          localStorage.removeItem('token');
          toast.error(`Ocurrio un error desconocido, intentalo más tarde`)
        });
      }

      if (err.status === 403) {

      }

      if (err.status === 500) {
        if (err.error.message.includes("DATABASE_QUERY_ERROR")) {
          const errorMsg = err.error.error.split('\'');
          toast.error(`Campo duplicado: ${errorMsg[1]}`)
        }else{
          toast.error(`${err.error.message}`)
        }
      }



      return throwError(() => {
        if (err.message.includes('0 Unknown Error')) {
          toast.error(`Ocurrio un error desconocido, intentalo más tarde`)
        }
      });
    }
    ))
}
