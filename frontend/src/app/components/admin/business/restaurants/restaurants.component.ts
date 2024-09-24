import { Component, inject, OnInit } from '@angular/core';
import { Restaurant } from '../../../../models/Restaurant';
import { RestaurantService } from '../../../../services/restaurant.service';
import { MatDialog } from '@angular/material/dialog';
import { RestaurantDialogComponent } from './restaurant-dialog/restaurant-dialog.component';
import { Router } from '@angular/router';
import { RestaurantTypeComponent } from './restaurant-type/restaurant-type.component';

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
    this.restaurantServices.getAllRestaurants().subscribe(res => { this.dataSource = res, console.log(res) })
  }

  restaurantDetails(restaurant: Restaurant) {
    this.router.navigate([`/admin/business/restaurants/details/`, restaurant.restaurantId]);
  }

  // Create new restaurant
  newRestaurant() {
    const dialogRef = this.dialog.open(RestaurantDialogComponent, {
      width: '500px',
    })
    dialogRef.afterClosed().subscribe((result) => {
      console.log(result)
    });
  }

  // Create new category
  newCategory() {
    const dialogRef = this.dialog.open(RestaurantTypeComponent, {
      width: '500px',
    })
  }




}
