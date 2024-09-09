import { Component, inject, OnInit } from '@angular/core';
import { Restaurant } from '../../../../models/Restaurant';
import { RestaurantService } from '../../../../services/restaurant.service';
import { MatDialog } from '@angular/material/dialog';
import { RestaurantDialogComponent } from './restaurant-dialog/restaurant-dialog.component';

@Component({
  selector: 'app-restaurants',
  templateUrl: './restaurants.component.html',
  styles: ``
})
export class RestaurantsComponent implements OnInit {
  private restaurantServices = inject(RestaurantService);
  private dialog = inject(MatDialog);
  public restaurants!: Restaurant[];
  public dataSource = this.restaurants;
  public displayedColumns: string[] = ['name', 'category', 'price', 'is_active', 'has_stock', 'actions'];


  ngOnInit(): void {
    this.loadRestaurants();
  }

  loadRestaurants(): void {
    this.restaurantServices.getAllRestaurants().subscribe(res => { this.dataSource = res, console.log(res) })
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
