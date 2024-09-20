import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RestaurantType } from '../models/RestaurantType';

@Injectable({
  providedIn: 'root'
})
export class RestaurantTypesService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/restaurants/types`;
  private http = inject(HttpClient);

  constructor() { }


  getAllTypes(): Observable<RestaurantType[]> {
    return this.http.get<RestaurantType[]>(`${this.urlEndPoint}`);
  }

}
