import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = "http://localhost:8080/api/employee";
  }

  public social_login() {
    return this.http.get("http://localhost:8080/oauth2/authorization/google");
  }

  public getAll(): Observable<any> {
    return this.http.get(this.apiUrl + "/getAll");
  }

  //TODO: implementation of method for search by name and last name
  public getEmployeeByEmail(email): Observable<any> {
    return this.http.get(this.apiUrl + "/getByEmail/" + email)
  }


}
