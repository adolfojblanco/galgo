import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ValueChangeEvent } from '@angular/forms';
import { RestaurantService } from '../../../../../services/restaurant.service';
import { HotToastService } from '@ngxpert/hot-toast';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { RestaurantType } from '../../../../../models/RestaurantType';
import { RestaurantTypesService } from '../../../../../services/restaurant-types.service';
import { catchError, of } from 'rxjs';

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

  constructor(
    private dialogRef: MatDialogRef<RestaurantDialogComponent>) {

  }

  restaurantForm: FormGroup = this.fb.group({
    restaurantId: [''],
    restaurantName: ['Villano', Validators.required],
    mobilePhone: ['567567567', [Validators.required, Validators.minLength(9)]],
    localPhone: ['567567567', [Validators.required, Validators.minLength(9)]],
    restaurantType: [, [Validators.required]],
    username: ['alexblanco'],
    firstName: ['Alejandro', [Validators.required]],
    lastName: ['Blanco', [Validators.required]],
    email: ['ajblanco156@gmail.com', [Validators.email]],
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
      // Create a restaurant
      this.restaurantService.createRestaurant(this.restaurantForm.value).pipe(
        this.toast.observe({
          loading: 'Guardando...',
          success: '🚀 Registro guardado',
          error: '😡 Hubo un error al guardar'
       })
      ).subscribe((res) => {
        this.dialogRef.close(res);
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
