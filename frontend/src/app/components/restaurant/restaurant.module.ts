import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RestaurantRoutingModule } from './restaurant-routing.module';
import { RestaurantComponent } from './restaurant.component';
import { MaterialModule } from '../shared/material/material.module';
import { DesktopComponent } from './desktop/desktop.component';
import { ProductsComponent } from './products/products.component';
import { ProductDialogComponent } from './products/product-dialog/product-dialog.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    RestaurantComponent,
    DesktopComponent,
    ProductsComponent,
    ProductDialogComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RestaurantRoutingModule,
    MaterialModule
  ]
})
export class RestaurantModule { }
