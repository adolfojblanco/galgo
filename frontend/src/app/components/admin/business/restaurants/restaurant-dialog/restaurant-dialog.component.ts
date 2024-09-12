import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HotToastService } from '@ngneat/hot-toast';
import { RestaurantService } from '../../../../../services/restaurant.service';

@Component({
  selector: 'app-restaurant-dialog',
  templateUrl: './restaurant-dialog.component.html',
  styles: ``
})
export class RestaurantDialogComponent {
  private toast = inject(HotToastService);
  private restaurantService = inject(RestaurantService);
  private fb = inject(FormBuilder);
  public titleForm = 'Nuevo Restaurante:';
  public textButton = 'Guardar';


  public restaurantForm: FormGroup = this.fb.group({
    restaurantId: [],
    restaurantName: ['Nombre'],
    manager: ['Manager'],
    mobilePhone: ['Mobile'],
    localPhone: ['Local']
  })

  submit() {
    if (this.restaurantForm.invalid) {
      this.toast.error("Todos los campos son requeridos")
      return;
    }
    if (this.restaurantForm.controls['restaurantId'].value) {
      // Edit restaurant
    } else {
      // Create a restaurant
      this.restaurantService.createRestaurant(this.restaurantForm.value).subscribe((res) => {
        this.toast.success(`${this.restaurantForm.controls['restaurantName'].value}, registrado correctamente`)
      });
    }

  }

  isValid(field: string) {
    return (
      this.restaurantForm.controls[field].errors &&
      this.restaurantForm.controls[field].touched
    );
  }
}
