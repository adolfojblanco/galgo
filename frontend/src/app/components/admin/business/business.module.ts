import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BusinessRoutingModule } from './business-routing.module';
import { BusinessComponent } from './business.component';
import { RestaurantsComponent } from './restaurants/restaurants.component';
import { MaterialModule } from '../../shared/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RestaurantDialogComponent } from './restaurants/restaurant-dialog/restaurant-dialog.component';
import { RestaurantDetailsComponent } from './restaurants/restaurant-details/restaurant-details.component';
import { AddressDialogComponent } from './address/address-dialog/address-dialog.component';
import { RestaurantTypeDialogComponent } from './restaurants/restaurant-type-dialog/restaurant-type-dialog.component';
import { RestaurantTypeComponent } from './restaurants/restaurant-type/restaurant-type.component';


@NgModule({
  declarations: [
    BusinessComponent,
    RestaurantsComponent,
    RestaurantDialogComponent,
    RestaurantDetailsComponent,
    AddressDialogComponent,
    RestaurantTypeComponent,
    RestaurantTypeDialogComponent,
  ],
  imports: [
    CommonModule,
    BusinessRoutingModule,
    MaterialModule,
    ReactiveFormsModule
  ]
})
export class BusinessModule { }
