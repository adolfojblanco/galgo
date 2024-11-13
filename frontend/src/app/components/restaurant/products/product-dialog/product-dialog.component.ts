import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../models/Category';
import { AuthService } from '../../../../services/auth.service';
import { ProductService } from '../../../../services/product.service';
import { RestaurantService } from '../../../../services/restaurant.service';
import { Restaurant } from '../../../../models/Restaurant';
import { ApiResponse } from '../../../../models/ApiResponse';

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styles: ``
})
export class ProductDialogComponent implements OnInit {
  private fb = inject(FormBuilder);

  private categoryService = inject(CategoryService);
  private authService = inject(AuthService);
  private restService = inject(RestaurantService);
  private productService = inject(ProductService);

  categories!: Category[];
  restaurant!: Restaurant;
  titleForm: string = "Nuevo Producto";
  textButton = 'Guardar'

  ngOnInit(): void {
    this.loadCategories()
    this.loadMyRestaurant()
  }

  loadCategories() {
    this.categoryService.getActiveCategories().subscribe((res: any) => {
      this.categories = res.data;
    })
  }

  loadMyRestaurant() {
    this.restService.getMyRestaurant().subscribe((res: any) => {
      this.restaurant = res.data
    });
  }

  productForm: FormGroup = this.fb.group({
    id: [],
    productName: [],
    price: [],
    description: [],
    category: [],
    enabled: true
  })

  submit() {
    this.loadMyRestaurant();
    this.productService.newProductFromRestaurant(this.restaurant.restaurantId!, this.productForm.value).subscribe((res) => console.log(res))
  }

  isValid(field: string) {
    return (
      this.productForm.controls[field].errors &&
      this.productForm.controls[field].touched
    );
  }
}
