import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AppService} from '../../services/app.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  welcome_message: string;

  constructor(private appService: AppService,
              private router: Router) {
      this.welcome_message = "Benvenuto in gestione presenze!"}




}
