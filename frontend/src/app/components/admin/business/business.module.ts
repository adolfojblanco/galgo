import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { BusinessComponent } from './business.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { MaterialModule } from '../../shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RestaurantDialogComponent } from './restaurants/restaurant-dialog/restaurant-dialog.component';
import { RestaurantDetailsComponent } from './restaurants/restaurant-details/restaurant-details.component';


@NgModule({
  declarations: [
    BusinessComponent,
    RestaurantsComponent,
    RestaurantDialogComponent,
    RestaurantDetailsComponent
  ],
  imports: [
    CommonModule,
    BusinessRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class BusinessModule { }