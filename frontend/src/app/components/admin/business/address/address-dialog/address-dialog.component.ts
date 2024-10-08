import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Address } from '../../../../../models/Address';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { AddressService } from '../../../../../services/address.service';
import { HotToastService } from '@ngxpert/hot-toast';
import { RestaurantService } from '../../../../../services/restaurant.service';
import { Restaurant } from '../../../../../models/Restaurant';

@Component({
  selector: 'app-address-dialog',
  templateUrl: './address-dialog.component.html',
  styles: ``
})
export class AddressDialogComponent {
  private fb = inject(FormBuilder);
  private toast = inject(HotToastService);
  private addressService = inject(AddressService);
  private restaurantService = inject(RestaurantService);
  public textButton: string = 'Guardar';
  public formTitle: string = 'Dirección';

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Restaurant,
    private dialogRef: MatDialogRef<AddressDialogComponent>) {
    if (data.address) {
      this.addressForm.reset(data.address)
      this.textButton = 'Edición'
    }
  }

  /** Address Form */
  public addressForm: FormGroup = this.fb.group({
    addressId: [],
    addressName: ['Principal', [Validators.required]],
    buildingNumber: ['Edificio', [Validators.required]],
    floorNumber: ['Piso', [Validators.required]],
    doorNumber: ['Puerta', [Validators.required]],
    street: ['Calle', [Validators.required]],
    area: ['Area', [Validators.required]],
    city: ['Ciudad', [Validators.required]],
    postalCode: ['Postal', [Validators.required, Validators.min(4)]],
    country: ['España', [Validators.required]],
    latitude: ['lat', [Validators.required]],
    longitude: ['long', [Validators.required]],
    active: [true],
  })


  submit() {
    if (this.addressForm.invalid) {
      this.toast.error("Todos los campos son obligatorios");
      return
    }
    if (this.addressForm.controls['addressId'].value) {
      this.addressService.editAddress(this.addressForm.value).subscribe((res) => {
        this.dialogRef.close(true)
      })
    } else {
        this.addressService.addRestaurantAddress(this.data.restaurantId!, this.addressForm.value).subscribe((res) => {
          this.dialogRef.close(res);
          this.toast.success("Direccción Registrada");
        }
      )
    }
  }

  /** Form error */
  getFieldError(field: string): string | null {
    if (!this.addressForm.controls[field]) return null;

    const errors = this.addressForm.controls[field].errors || {};

    for (const key of Object.keys(errors)) {
      switch (key) {
        case 'required':
          return 'Este campo es requerido';
        case 'minlength':
          return `Mínimo ${errors['minlength'].requiredLength} caracteres`;
      }
    }
    return null;
  }

  /** Form error */
  isValid(field: string) {
    return (
      this.addressForm.controls[field].errors &&
      this.addressForm.controls[field].touched
    );
  }

}
