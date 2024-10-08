import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BusinessComponent } from './business.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { RestaurantDetailsComponent } from './restaurants/restaurant-details/restaurant-details.component';
import { RestaurantTypeComponent } from './restaurants/restaurant-type/restaurant-type.component';

const routes: Routes = [
  {
    path: '',
    component: BusinessComponent,
    children: [
      {
        path: 'restaurants',
        component: RestaurantsComponent
      },
      {
        path: 'restaurants-types',
        component: RestaurantTypeComponent
      },
      {
        path: 'restaurants/details/:id',
        component: RestaurantDetailsComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BusinessRoutingModule { }
