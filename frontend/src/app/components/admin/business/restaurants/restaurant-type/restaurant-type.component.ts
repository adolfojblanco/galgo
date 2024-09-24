import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HotToastService } from '@ngxpert/hot-toast';

@Component({
  selector: 'app-restaurant-type',
  templateUrl: './restaurant-type.component.html',
  styles: ``
})
export class RestaurantTypeComponent {
  private toast = inject(HotToastService);
  private fb = inject(FormBuilder)

  public titleForm = 'Nueva Categoria'
  public textButton = 'Guardar'

  typeForm: FormGroup = this.fb.group({
    name: [],
  })

  submit(){}


  isValid(field: string) {
    return (
      this.typeForm.controls[field].errors &&
      this.typeForm.controls[field].touched
    );
  }

}
