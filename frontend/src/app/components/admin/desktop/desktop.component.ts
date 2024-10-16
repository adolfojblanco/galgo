import { Component, inject, OnInit } from '@angular/core';
import { RestaurantService } from '../../../services/restaurant.service';
import { Restaurant } from '../../../models/Restaurant';
import { AuthService } from '../../../services/auth.service';
import { User } from '../../../models/User';
import { Role } from '../../../models/Role';

@Component({
  selector: 'app-desktop',
  templateUrl: './desktop.component.html',
  styles: ``
})
export class DesktopComponent implements OnInit {

  private authService = inject(AuthService);
  private restService = inject(RestaurantService);

  public user!: User;

  public restaurant!: Restaurant

  ngOnInit(): void {
    this.loadUser();

  }

  loadUser() {
    this.user = this.authService.getAuthUser();
    if (this.user.authorities?.includes(Role.BUSINESS)) {
      this.loadMyRestaurant();
    }
  }

  loadMyRestaurant() {
    this.restService.getMyRestaurant().subscribe((res: any) => {
      this.restaurant = res.data
    })
  }

  enabledRestaurant() {
    this.restService.activateRestaurant(this.restaurant.restaurantId!).subscribe((res:any) => {
      this.restaurant.enabled = !this.restaurant.enabled
    })

  }
}
