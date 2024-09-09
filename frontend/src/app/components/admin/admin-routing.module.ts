import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { DesktopComponent } from './desktop/desktop.component';

const routes: Routes = [
  {
    path: '',
    component: AdminComponent,
    children: [
      {
        path: '',
        component: DesktopComponent,
      },
      {
        path: 'business',
        loadChildren: () =>
          import('./business/business.module').then((b) => b.BusinessModule),
      },
    ]
  },
  {
    path: '**',
    redirectTo: '/admin',
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
