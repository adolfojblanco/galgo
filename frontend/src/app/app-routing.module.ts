import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { isAdminGuard } from './guards/is-admin.guard';

const routes: Routes = [
  {
    path: 'admin',
    canActivate: [isAdminGuard],
    loadChildren: () =>
      import('./components/admin/admin.module').then((a) => a.AdminModule),
  },
  {
    path: 'my-restaurant',
    loadChildren: () => import('./components/restaurant/restaurant.module').then((r) => r.RestaurantModule)
  },
  {
    path: 'auth',
    loadChildren: () =>
      import('./components/auth/auth.module').then((a) => a.AuthModule),
  },
  {
    path: '**',
    redirectTo: '/auth/login',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
