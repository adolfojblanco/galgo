import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { RestaurantService } from '../../../../../services/restaurant.service';
import { Restaurant } from '../../../../../models/Restaurant';
import { AddressDialogComponent } from '../../address/address-dialog/address-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styles: ``
})
export class RestaurantDetailsComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private dialog = inject(MatDialog);
  private restaurantService = inject(RestaurantService);
  public restaurant: Restaurant = {
    restaurantName: '',
    manager: '',
    mobilePhone: '',
    localPhone: '',
    email: ''
  };

  ngOnInit(): void {
    this.route.params
      .pipe(switchMap(({ id }) => this.restaurantService.findOneById(id)))
      .subscribe((res) => {
        this.restaurant = res;
        console.log(this.restaurant)
      });
  }


  // Add Address dialog

  // Create new restaurant
  newAddress() {
    const dialogRef = this.dialog.open(AddressDialogComponent, {
      width: '650px',
    })
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {

      }
    });
  }

}
