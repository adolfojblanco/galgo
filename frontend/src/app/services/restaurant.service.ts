
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Restaurant } from '../models/Restaurant';
import { HttpClient } from '@angular/common/http';

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
  findOneById(id: number): Observable<Restaurant> {
    return this.http.get<Restaurant>(`${this.urlEndPoint}/${id}`);
  }
}
