import { Component } from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styles: ``
})
export class AdminComponent {

  public sidebarItems = [
    { label: 'Inicio', icon: 'home', url: './' },
    { label: 'Categorias', icon: 'category', url: './business/restaurants-types' },
    { label: 'Tiendas', icon: 'storefront', url: './stores' },
    { label: 'Restaurantes', icon: 'restaurant', url: './business/restaurants' },
    { label: 'Usuarios', icon: 'groups', url: './users' },
  ];


}