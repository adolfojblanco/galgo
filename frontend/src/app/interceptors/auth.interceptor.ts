import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
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

  if (token) {
    const authReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });

    return next(authReq).pipe(
      catchError((err: HttpErrorResponse) => {
        if (err.status == 401) {
          console.log("401", err)
          return throwError(() => {
            localStorage.removeItem('token');
            router.navigate(['(auth/login)'])
            toast.info('Debes volver a loguarte');
            toast.error(`${err.error.message}: ${err.error.error}`);
          })
        }

        if (err.status === 403) {
          toast.error("No estas autorizado")
        }

        if (err.status === 400) {
          toast.error("ERROR 400")
        }

      if (err.status === 500) {
        if (err.error.error.includes("Duplicate entry")) {
          const errorMsg = err.error.error.split('\'');
          toast.error(`Campo duplicado: ${errorMsg[1]}`)
        }else if(err.error.error.includes("foreign key")){
          toast.error(`No se puede eliminar, es un campo relacionado`)
        }else{
          toast.error(`${err.error.message}`)
        }
      }

        return throwError(() => {
          toast.error("ERROR")
        });
      })
    );
  }
  return next(req).pipe(
    catchError((err: HttpErrorResponse) => {
      if (err.status == 401) {
        return throwError(() => {
          localStorage.removeItem('token');
          router.navigate(['(auth/login)'])
          toast.error(`${err.error.message}`);
        })
      }

      if (err.status === 403) {
        router.navigate(['(auth/login)'])
        toast.error("ERROR 403")
      }

      if (err.status === 400) {
        toast.error("ERROR 400")
      }

      return throwError(() => {
        toast.error("ERROR")
      });
    })
  );
}
