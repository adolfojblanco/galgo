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

  constructor(@Inject(MAT_DIALOG_DATA) public data: Restaurant, private dialogRef: MatDialogRef<AddressDialogComponent>) {
    console.log(data)
    if (data.address) {
      this.addressForm.reset(data.address)
      this.textButton = 'Edición'
    }

  }

  /** Address Form */
  public addressForm: FormGroup = this.fb.group({
    addressId: [],
    name: ['', [Validators.required]],
    street: ['', [Validators.required]],
    buildingNumber: ['', [Validators.required]],
    postalCode: ['', [Validators.required, Validators.min(4)]],
    floorNumber: ['', [Validators.required]],
    doorNumber: ['', [Validators.required]],
    area: ['', [Validators.required]],
    city: ['', [Validators.required]],
    country: ['', [Validators.required]],
    latitude: ['', [Validators.required]],
    longitude: ['', [Validators.required]],
    active: [],
  })


  submit() {
    if (this.addressForm.invalid) {
      this.toast.error("Todos los campos son obligatorios");
      return
    }
    if (this.addressForm.controls['addressId'].value) {
      this.addressService.editAddress(this.addressForm.value).subscribe((res) => {
        console.log(res)
        this.dialogRef.close(true)
      })
    } else {
      this.restaurantService.addAddress(this.data.restaurantId!, this.addressForm.value).subscribe((res: Restaurant) => {
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
