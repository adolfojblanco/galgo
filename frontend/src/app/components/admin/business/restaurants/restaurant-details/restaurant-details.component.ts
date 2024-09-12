import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { RestaurantService } from '../../../../../services/restaurant.service';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styles: ``
})
export class RestaurantDetailsComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private restaurantService = inject(RestaurantService);


  ngOnInit(): void {
    this.route.params
      .pipe(switchMap(({ id }) => this.restaurantService.findOneById(id)))
      .subscribe((res) => {
        console.log(res);
      });
  }

}
