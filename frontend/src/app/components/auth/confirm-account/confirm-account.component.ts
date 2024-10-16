import { Component, inject } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { HotToastService } from '@ngxpert/hot-toast';


@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styles: ``
})
export class ConfirmAccountComponent {
  private authService = inject(AuthService);
  private route = inject(ActivatedRoute)
  private router = inject(Router)
  private fb = inject(FormBuilder)
  private toast = inject(HotToastService);

  private token:  string | null = '';


  confirmForm: FormGroup = this.fb.group({
    username: ['alexblanco', [Validators.required]],
    password: ['', [Validators.required]],
  })

  constructor() {
    this.getToken()
  }

  getToken(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.token = params.get('token');
    });
  }

  submit() {
    console.log(this.confirmForm.value)
    this.authService.confirmAccount(this.token!, this.confirmForm.value).subscribe((res) => {
      if (res !== null) {
        Swal.fire({
          position: "top-end",
          icon: "success",
          title: "Tu cuenta se activo correctamente",
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigate(['/auth/login'])
      }else{
        this.toast.error("Hay un error en los datos, confirmalos y vuelve a intentarlo")
      }
    })

  }

}
