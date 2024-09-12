import { Component, inject, OnInit } from '@angular/core';
import { Restaurant } from '../../../../models/Restaurant';
import { RestaurantService } from '../../../../services/restaurant.service';
import { MatDialog } from '@angular/material/dialog';
import { RestaurantDialogComponent } from './restaurant-dialog/restaurant-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styles: ``
})
export class RestaurantsComponent implements OnInit {
  private restaurantServices = inject(RestaurantService);
  private router = inject(Router);
  private dialog = inject(MatDialog);
  public restaurants!: Restaurant[];
  public dataSource = this.restaurants;
  public displayedColumns: string[] = ['restaurantName', 'manager', 'mobilePhone', 'localPhone', 'enabled', 'actions'];


  ngOnInit(): void {
    this.loadRestaurants();
  }

  loadRestaurants(): void {
    this.restaurantServices.getAllRestaurants().subscribe(res => { this.dataSource = res })
  }

  restaurantDetails(restaurant: Restaurant) {
    this.router.navigate([`/admin/business/restaurants/details/`, restaurant.restaurantId]);
  }



  newRestaurant() {
    const dialogRef = this.dialog.open(RestaurantDialogComponent, {
      width: '450px',
    })
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.loadRestaurants()
      }
    });
  }



}
