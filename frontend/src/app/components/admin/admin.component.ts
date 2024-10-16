import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/User';
import { Role } from '../../models/Role';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styles: ``
})
export class AdminComponent implements OnInit {

  public user!: User;
  public sidebarItems = [
    { label: '', icon: '', url: '' },
  ]
  private authService = inject(AuthService);



  ngOnInit(): void {
    this.loadUser();
  }

  loadUser() {
    this.user = this.authService.getAuthUser();
    if (this.user.authorities?.includes(Role.BUSINESS)) {
      this.sidebarItems = this.sidebarBusinessItems
    } else {
      this.sidebarItems = this.sidebarAdminItems
    }
  }

  logout() {
    this.authService.logout();
  }

  public sidebarBusinessItems = [
    { label: 'Inicio', icon: 'home', url: './' },
  ];

  public sidebarAdminItems = [
    { label: 'Inicio', icon: 'home', url: './' },
    { label: 'Categorias', icon: 'category', url: './business/restaurants-types' },
    //{ label: 'Tiendas', icon: 'storefront', url: './stores' },
    { label: 'Restaurantes', icon: 'restaurant', url: './business/restaurants' },
    { label: 'Usuarios', icon: 'groups', url: './users' },
  ]


}