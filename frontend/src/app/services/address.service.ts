import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Address } from '../models/Address';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/address`;
  private http = inject(HttpClient);
  constructor() { }


  /**
    New Address
  */
  newAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(`${this.urlEndPoint}`, address);
  }

  addRestaurantAddress(restaurantId: number ,address: Address) {
    return this.http.post<Address>(`${this.urlEndPoint}/${restaurantId}/add-address`, address);
  }

  editAddress(address: Address): Observable<Address> {
    return this.http.put<Address>(`${this.urlEndPoint}/${address.addressId}`, address)
  }

}
