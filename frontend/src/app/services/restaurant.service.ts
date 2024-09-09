
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

  getAllRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(`${this.urlEndPoint}`);
  }
}
