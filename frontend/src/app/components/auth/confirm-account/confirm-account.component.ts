import { Component, inject } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-confirm-account',
  templateUrl: './confirm-account.component.html',
  styles: ``
})
export class ConfirmAccountComponent {
  private authService = inject(AuthService);
  private route = inject(ActivatedRoute)
  private router = inject(ActivatedRoute)
  private fb = inject(FormBuilder)

  private username: string = ''

  confirmForm: FormGroup = this.fb.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]],
  })

  constructor() {
    this.getToken()
  }

  getToken(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const token2 = this.route.snapshot.paramMap.get('token')
      const token: any = params.get('token');
      console.log(token2);
      // this.authService
      //   .confirmAccount(token, this.username)
      //   .subscribe();
    });
  }

  submit() {

  }

}
