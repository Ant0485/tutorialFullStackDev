import { Component, OnInit } from '@angular/core';
import { AppService } from '../../services/app.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private app_service: AppService) { }

  public social_login() {
    console.log("Called Login with Google")
    this.app_service.social_login();
  }

}
