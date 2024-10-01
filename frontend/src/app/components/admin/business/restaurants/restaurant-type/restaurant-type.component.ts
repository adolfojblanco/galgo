import { Component, inject, OnInit } from '@angular/core';
import { RestaurantType } from '../../../../../models/RestaurantType';
import { RestaurantTypesService } from '../../../../../services/restaurant-types.service';
import { RestaurantTypeDialogComponent } from '../restaurant-type-dialog/restaurant-type-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-restaurant-type',
  templateUrl: './restaurant-type.component.html',
  styles: ``
})
export class RestaurantTypeComponent implements OnInit {
  public restaurantsTypes!: RestaurantType[];
  public dataSource = this.restaurantsTypes;
  private dialog = inject(MatDialog)

  private restTypeService = inject(RestaurantTypesService)
  public displayedColumns: string[] = ['name', 'enabled', 'actions'];

  ngOnInit(): void {
    this.loadRestTypes();
  }

  loadRestTypes(): void {
    this.restTypeService.getAllTypes().subscribe((res) => {
      this.dataSource = res
    })
  }

  editRestType(restype: RestaurantType) {
    const dialogRef = this.dialog.open(RestaurantTypeDialogComponent, {
      width: '450px',
      data: restype
    })
    dialogRef.afterClosed().subscribe((res) => {
      if (res) {
        this.loadRestTypes()
      }
    });
  }

  addRestType() {
    const dialogRef = this.dialog.open(RestaurantTypeDialogComponent, {
      width: '450px',
    })
    dialogRef.afterClosed().subscribe((res) => {
      if (res) {
        this.loadRestTypes()
      }
    });
  }

  delete(restType: RestaurantType) {
    Swal.fire({
      title: "Estas seguro?",
      text: "Esta accion no se puede revertir!",
      icon: "warning",
      cancelButtonText: "Cancelar",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Si, borralo!"
    }).then((result) => {
      if (result.isConfirmed) {
        this.restTypeService.deleteRestType(restType).subscribe((res) => {
          if (res === null) {
            Swal.fire({
              title: "Eliminado!",
              text: "Se elimino correctamente.",
              icon: "success"
            });
            this.loadRestTypes();
            ;

          }
        })
      }
    });
  }
}
