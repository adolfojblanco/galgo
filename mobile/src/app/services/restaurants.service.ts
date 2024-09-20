import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Restaurant } from '../models/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantsService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/restaurants`;
  private http = inject(HttpClient);
  constructor() { }


  getAllRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(`${this.urlEndPoint}`);
  }
}

