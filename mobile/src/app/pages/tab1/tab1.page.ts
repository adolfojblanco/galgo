import { Component, inject, OnInit } from '@angular/core'
import { RestaurantsService } from '../../services/restaurants.service';
import { Restaurant } from '../../models/restaurant';
import { register } from 'swiper/element/bundle';

register();

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
    this.restaurantsService.getAllRestaurants().subscribe((res:any) => {
      this.restaurants = res.data;
    })
  }


}
