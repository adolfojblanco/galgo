import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValueChangeEvent } from '@angular/forms';
import { RestaurantService } from '../../../../../services/restaurant.service';
import { HotToastService } from '@ngxpert/hot-toast';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { RestaurantType } from '../../../../../models/RestaurantType';
import { RestaurantTypesService } from '../../../../../services/restaurant-types.service';

@Component({
  selector: 'app-restaurant-dialog',
  templateUrl: './restaurant-dialog.component.html',
  styles: ``
})
export class RestaurantDialogComponent implements OnInit {
  private dialog = inject(MatDialog);
  private toast = inject(HotToastService);
  private fb = inject(FormBuilder);

  public restaurantType!: RestaurantType[];
  public titleForm = 'Nuevo Restaurante:';
  public textButton = 'Guardar';
  private restaurantService = inject(RestaurantService);
  private restaurantTypeService = inject(RestaurantTypesService);

  constructor(private dialogRef: MatDialogRef<RestaurantDialogComponent>) { }

  public restaurantForm: FormGroup = this.fb.group({
    restaurantId: [''],
    restaurantName: ['Villano', Validators.required],
    manager: ['Alejandro Blanco', [Validators.required]],
    email: ['alse@gmail.com', [Validators.email]],
    mobilePhone: ['567567567', [Validators.required, Validators.minLength(9)]],
    localPhone: ['567567567', [Validators.required, Validators.minLength(9)]],
    restaurantType: [, [Validators.required]]
  })

  ngOnInit(): void {
    this.loadRestaurantTypes();
  }

  loadRestaurantTypes(): void {
    this.restaurantTypeService.getAllTypes().subscribe((res) => {
      this.restaurantType = res;
    })
  }

  submit() {
    if (this.restaurantForm.invalid) {
      this.toast.error("Todos los campos son requeridos")
      return;
    }
    if (this.restaurantForm.controls['restaurantId'].value) {
      // Edit restaurant
    } else {

      console.log(this.restaurantForm.value)
      // Create a restaurant
      this.restaurantService.createRestaurant(this.restaurantForm.value).subscribe((res) => {
        this.toast.success(`${this.restaurantForm.controls['restaurantName'].value}, registrado correctamente`)
        this.dialogRef.close();
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
