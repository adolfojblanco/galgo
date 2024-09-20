import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { HotToastService } from '@ngxpert/hot-toast';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
    `
    .login {
      padding-top: 30px;
    }
    .logo {
      width: 350px;
    }
  `,
  ]
})
export class LoginComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private toast = inject(HotToastService);


  public loginForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  });


  login() {
    this.authService
      .login(this.loginForm.value)
      .subscribe((res) => {
        console.log(res)
      });
  }
}
