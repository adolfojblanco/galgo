import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Category } from '../models/Category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private readonly urlEndPoint: string = `${environment.apiUrl}/categories`;
  private http = inject(HttpClient);
  constructor() { }

  getActiveCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.urlEndPoint}/active`)
  }
}
