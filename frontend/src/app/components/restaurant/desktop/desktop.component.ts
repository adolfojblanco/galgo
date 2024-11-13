import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { RestaurantService } from '../../../services/restaurant.service';
import { Restaurant } from '../../../models/Restaurant';

@Component({
  selector: 'app-desktop',
  templateUrl: './desktop.component.html',
  styles: ``
})
export class DesktopComponent implements OnInit {

  private restService = inject(RestaurantService);
  restaurant!: Restaurant;

  ngOnInit(): void {
    this.loadMyRestaurant()
  }

  loadMyRestaurant() {
    this.restService.getMyRestaurant().subscribe((res:any) => {
      this.restaurant = res.data;
    });
  }








}
