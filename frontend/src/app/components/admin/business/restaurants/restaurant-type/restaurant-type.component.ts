import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HotToastService } from '@ngxpert/hot-toast';
import { RestaurantTypesService } from '../../../../../services/restaurant-types.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-restaurant-type',
  templateUrl: './restaurant-type.component.html',
  styles: ``
})
export class RestaurantTypeComponent {
  private toast = inject(HotToastService);
  private fb = inject(FormBuilder)
  private dialogRef = inject(MatDialogRef<RestaurantTypeComponent>);


  private restTypesServices = inject(RestaurantTypesService);

  public titleForm = 'Nueva Categoria'
  public textButton = 'Guardar'

  typeForm: FormGroup = this.fb.group({
    name: ['', [Validators.required, Validators.min(3)]],
  })

  submit() {
    this.restTypesServices.createType(this.typeForm.value).subscribe((res) => {
      this.typeForm.reset()
      this.toast.success(`Registrado correctamente`)
      this.dialogRef.close();
    })
  }


  isValid(field: string) {
    return (
      this.typeForm.controls[field].errors &&
      this.typeForm.controls[field].touched
    );
  }

}
