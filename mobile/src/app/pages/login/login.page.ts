import { Component, OnInit } from '@angular/core';
import { register } from 'swiper/element/bundle';

register();

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor() { }

  ngOnInit() {
    return null;
  }

}
