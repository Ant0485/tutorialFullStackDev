import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = "http://localhost:8080/api/employee";
  }

  public social_login() {
    this.http.get("http://localhost:8080/oauth2/authorization/google");
  }


}
