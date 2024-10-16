import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantComponent } from './restaurant.component';

import { ProductsComponent } from './products/products.component';
import { DesktopComponent } from './desktop/desktop.component';

const routes: Routes = [
  {
    path: '',
    component: RestaurantComponent,
    children: [
      {
        path: '',
        component: DesktopComponent,
      },
      {
        path: 'products',
        component: ProductsComponent
      }
    ]
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RestaurantRoutingModule { }
