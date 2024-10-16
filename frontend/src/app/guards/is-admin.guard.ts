import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Role } from '../models/Role';

export const isAdminGuard: CanActivateFn = (route, state) => {

  const authServices = inject(AuthService);
  const router = inject(Router)
  const url = state.url

  if (authServices.hasRoles(Role.ADMIN)) {
    console.log("SOY ADMIN" ,authServices.hasRoles(Role.ADMIN))
    return true;
  }
  router.navigateByUrl('/my-restaurant')
  console.log("NO SOY ADMIN" ,authServices.hasRoles(Role.ADMIN))
  return false;
};
