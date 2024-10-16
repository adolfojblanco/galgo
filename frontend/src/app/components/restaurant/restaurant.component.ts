import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { RestaurantService } from '../../services/restaurant.service';

@Component({
  selector: 'app-restaurant',
  templateUrl: './restaurant.component.html',
  styles: ``
})
export class RestaurantComponent {

  private authService = inject(AuthService)
  private restaurantService = inject(RestaurantService)



  sidebarItems = [
    { label: 'Inicio', icon: 'home', url: './' },
    { label: 'Productos', icon: 'inventory', url: './/products' },
    { label: 'Mis Pedidos', icon: 'list_alt', url: './' },
    { label: 'Mis Ventas', icon: 'savings', url: './' },
  ]


  logout() {
    this.authService.logout()
  }
}
