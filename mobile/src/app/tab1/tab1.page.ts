import { Component, inject, OnInit } from '@angular/core'
import { register } from 'swiper/element/bundle';
import { RestaurantsService } from '../services/restaurants.service';
import { Restaurant } from '../models/restaurant';
register()
@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss']
})
export class Tab1Page implements OnInit {

  private restaurantsService = inject(RestaurantsService);
  public restaurants!: Restaurant[];

  constructor() { }


  ngOnInit(): void {
    this.loadRestaurants()
  }


  loadRestaurants() {
    this.restaurantsService.getAllRestaurants().subscribe((res) => {
      this.restaurants = res;
    })
  }


}
