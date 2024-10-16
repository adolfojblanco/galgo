import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CategoryService } from '../../../../services/category.service';
import { Category } from '../../../../models/Category';

@Component({
  selector: 'app-product-dialog',
  templateUrl: './product-dialog.component.html',
  styles: ``
})
export class ProductDialogComponent implements OnInit {

  private categoryService = inject(CategoryService);
  private fb = inject(FormBuilder);
  categories!: Category[];
  titleForm: string = "Nuevo Producto";
  textButton = 'Guardar'

  ngOnInit(): void {
    this.loadCategories()
  }

  loadCategories() {
    this.categoryService.getActiveCategories().subscribe((res:any) => {
      this.categories = res.data;
    })
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
    console.log(this.productForm.value)
  }

  isValid(field: string) {
    return (
      this.productForm.controls[field].errors &&
      this.productForm.controls[field].touched
    );
  }
}
