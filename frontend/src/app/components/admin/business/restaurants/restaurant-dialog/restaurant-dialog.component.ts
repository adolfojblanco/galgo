import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-restaurant-dialog',
  templateUrl: './restaurant-dialog.component.html',
  styles: ``
})
export class RestaurantDialogComponent {
  private fb = inject(FormBuilder);
  public titleForm = 'Nuevo Restaurante:';
  public textButton = 'Guardar';


  public restaurantForm: FormGroup = this.fb.group({
    restaurantId: [],
    restaurantName: [],
    manager: [],
    mobilePhone: [],
    localPhone: []
  })

  submit() { }

  isValid(field: string) {
    return (
      this.restaurantForm.controls[field].errors &&
      this.restaurantForm.controls[field].touched
    );
  }
}
