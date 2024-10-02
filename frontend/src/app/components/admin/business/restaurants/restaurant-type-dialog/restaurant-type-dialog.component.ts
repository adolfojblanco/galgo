import { Component, Inject, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { HotToastService } from '@ngxpert/hot-toast';
import { RestaurantTypesService } from '../../../../../services/restaurant-types.service';
import { RestaurantType } from '../../../../../models/RestaurantType';

@Component({
  selector: 'app-restaurant-type-dialog',
  templateUrl: './restaurant-type-dialog.component.html',
  styles: ``
})
export class RestaurantTypeDialogComponent {
  private toast = inject(HotToastService);
  private fb = inject(FormBuilder)
  private dialogRef = inject(MatDialogRef<RestaurantTypeDialogComponent>);
  private restTypesServices = inject(RestaurantTypesService);

  public titleForm = 'nueva categoria'
  public textButton = 'Guardar'

  constructor(
    @Inject(MAT_DIALOG_DATA) data: RestaurantType) {
    if (data) {
      this.titleForm = "Edición de Categoria"
      this.typeForm.reset(data)
      this.textButton = 'Edición'
    }
  }

  typeForm: FormGroup = this.fb.group({
    id: [],
    name: ['', [Validators.required, Validators.min(3)]],
    enabled: []
  })

  submit() {
    if (this.typeForm.controls['id'].value) {
      this.restTypesServices.updateRestType(this.typeForm.value).subscribe((res) => {
        this.toast.success(`${this.typeForm.controls['name'].value}, Actualizado correctamente`)
        this.typeForm.reset()
        this.dialogRef.close(true);
      })
    } else {
      this.restTypesServices.createType(this.typeForm.value).subscribe((res) => {
        this.toast.success(`${this.typeForm.controls['name'].value}, Registrado correctamente`)
        this.typeForm.reset()
        this.dialogRef.close(true);
      })
    }
  }


  isValid(field: string) {
    return (
      this.typeForm.controls[field].errors &&
      this.typeForm.controls[field].touched
    );
  }
}
