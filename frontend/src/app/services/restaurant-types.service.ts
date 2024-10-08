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

  createType(type: RestaurantType): Observable<RestaurantType> {
    return this.http.post<RestaurantType>(`${this.urlEndPoint}`, type)
  }

  updateRestType(type: RestaurantType): Observable<RestaurantType> {
    return this.http.put<RestaurantType>(`${this.urlEndPoint}/${type.id}`, type)
  }

  getAllActivateTypes(): Observable<RestaurantType[]>{
    return this.http.get<RestaurantType[]>(`${this.urlEndPoint}/active`);
  }

  deleteRestType(type: RestaurantType): Observable<boolean> {
    return this.http.delete<boolean>(`${this.urlEndPoint}/${type.id}`);
  }

}
