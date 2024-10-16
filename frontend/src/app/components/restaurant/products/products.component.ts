import { Component, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProductDialogComponent } from './product-dialog/product-dialog.component';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styles: ``
})
export class ProductsComponent {
  readonly dialog = inject(MatDialog);

  products!: any[];
  displayedColumns: string[] = ['id', 'name', 'price', 'enabled'];
  dataSource = this.products

  newProduct() {
    this.dialog.open(ProductDialogComponent, {
      width: '650px'
    })
  }

}
