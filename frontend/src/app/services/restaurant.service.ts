
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { map, Observable } from 'rxjs';
import { Restaurant } from '../models/Restaurant';
import { HttpClient } from '@angular/common/http';
import { Address } from '../models/Address';
import { ApiResponse } from '../models/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/restaurants`;
  private http = inject(HttpClient);
  constructor() { }

  /**
   * Get All restaurant
   * @returns
   */
  getAllRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(`${this.urlEndPoint}`);
  }

  /**
   * Create a restaurant
   * @param restaurant
   * @returns
   */
  createRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(`${this.urlEndPoint}`, restaurant);
  }

  /**
   * Search restaurant for id
   * @param id
   * @returns
   */
  findOneById(id: Number): Observable<Restaurant> {
    return this.http.get<Restaurant>(`${this.urlEndPoint}/${id}`);
  }

  /**
   * Add address to restaurant
   * @param restaurantId
   * @param address
   * @returns restaurant
   */
  addAddress(id: Number, address: Address): Observable<Restaurant> {
    return this.http.put<Restaurant>(`${this.urlEndPoint}/${id}/add-address`, address);
  }

  /** activate or deactivate a restaurant */
  activateRestaurant(id: Number): Observable<Restaurant> {
    return this.http.put<Restaurant>(`${this.urlEndPoint}/${id}/activate`, id);
  }

  getMyRestaurant(): Observable<Restaurant> {
    return this.http.get<Restaurant>(`${this.urlEndPoint}/my-restaurant`);
  }


}
