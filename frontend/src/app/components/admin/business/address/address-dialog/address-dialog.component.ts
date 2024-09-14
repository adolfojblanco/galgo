import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Address } from '../../../../../models/Address';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HotToastService } from '@ngneat/hot-toast';
import { AddressService } from '../../../../../services/address.service';

@Component({
  selector: 'app-address-dialog',
  templateUrl: './address-dialog.component.html',
  styles: ``
})
export class AddressDialogComponent {

  private fb = inject(FormBuilder);
  private toast = inject(HotToastService);
  private addressService = inject(AddressService);
  public textButton: string = 'Guardar';
  public formTitle: string = 'Dirección';

  constructor(@Inject(MAT_DIALOG_DATA) public data: Address, private dialogRef: MatDialogRef<AddressDialogComponent>) { }

  /** Address Form */
  public addressForm: FormGroup = this.fb.group({
    addressId: [],
    name: ['Principal'],
    street: ['Pz. Manuel Murguia'],
    buildingNumber: ['11'],
    postalCode: ['Postal'],
    floorNumber: ['Bj'],
    doorNumber: ['13'],
    area: ['Oseiro'],
    city: ['Arteixo'],
    state: ['Arteixo'],
    country: ['España'],
    latitude: ['lat'],
    longitude: ['lon'],
    active: [],
  })


  submit() {
    console.log('Llegasmos')
    if (this.addressForm.invalid) {
      this.toast.error("Todos los campos son obligatorios");
      return
    }
    if (this.addressForm.controls['addressId'].value) {
      // Edit Address
    } else {
      console.log("Nueva Direccion")
      this.addressService.newAddress(this.addressForm.value).subscribe(res => this.toast.success("Direccción Registrada"))
    }
  }

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

  isValid(field: string) {
    return (
      this.addressForm.controls[field].errors &&
      this.addressForm.controls[field].touched
    );
  }

}
