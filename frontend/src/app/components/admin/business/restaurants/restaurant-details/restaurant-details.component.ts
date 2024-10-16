import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { RestaurantService } from '../../../../../services/restaurant.service';
import { Restaurant } from '../../../../../models/Restaurant';
import { AddressDialogComponent } from '../../address/address-dialog/address-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import * as L from 'leaflet';
import { Address } from '../../../../../models/Address';
import { HotToastService } from '@ngxpert/hot-toast';
@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styles: `

  `
})
export class RestaurantDetailsComponent implements OnInit {
  private toast = inject(HotToastService);
  private route = inject(ActivatedRoute);
  private dialog = inject(MatDialog);
  private restaurantService = inject(RestaurantService);

  private map: any;
  private marker?: L.Marker<any> | undefined;

  public address!: Address;
  public restaurant: Restaurant = {
    restaurantName: '',
    mobilePhone: '',
    localPhone: '',
    enabled: false,
  };


  ngOnInit(): void {
    this.route.params
      .pipe(switchMap(({ id }) => this.restaurantService.findOneById(id)))
      .subscribe((res) => {
        this.restaurant = res;
        if (this.restaurant.address) {
          this.address = this.restaurant.address;
          this.initMap(Number(this.address?.latitude), Number(this.address?.longitude));
        }
      });
  }

  loadRestaurant(): void {
    this.restaurantService.findOneById(this.restaurant.restaurantId!).subscribe((res) => {
      this.restaurant = res;
    })
  }

  /**
   * Init map
   * @param lat in number
   * @param lon in number
   */
  private initMap(lat: number, lon: number): void {
    this.map = L.map('map').setView([lat, lon], 18);
    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; GalGo',
    }).addTo(this.map);
    this.marker = L.marker([lat, lon]).addTo(this.map)
  }

  /**
   * create a new address
   */
  newAddress() {
    const dialogRef = this.dialog.open(AddressDialogComponent, {
      width: '650px',
      data: this.restaurant
    })
    dialogRef.afterClosed().subscribe((res) => {
      this.loadRestaurant();
    });
  }

  /**
   * Edit address
   */
  editAddress() {
    console.log(this.address)
    const dialogRef = this.dialog.open(AddressDialogComponent, {
      width: '650px',
      data: this.address
    })
    // dialogRef.afterClosed().subscribe((result) => {

    // });

  }

  activateRestaurant(restaurant: Restaurant) {
    console.log(restaurant)
    this.restaurantService.activateRestaurant(restaurant.restaurantId!).subscribe((res:any) => {
      if (res.error) {
        this.toast.error(`${res.error}`)
        return;
      }
      this.toast.success(`Se activo correctamente, ${res.restaurantName}`)
    })
  }

}
