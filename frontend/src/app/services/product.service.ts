import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { map, Observable } from 'rxjs';
import { Product } from '../models/Product';
import { ApiResponse } from '../models/ApiResponse';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/products`;
  private http = inject(HttpClient);

  constructor() { }

  newProductFromRestaurant(restaurantId: number, product: Product): Observable<Product> {
    return this.http.post<ApiResponse>(`${this.urlEndPoint}/${restaurantId}/new-product`, product).pipe(
      map(({ data }) => product)
    )

  }
}
