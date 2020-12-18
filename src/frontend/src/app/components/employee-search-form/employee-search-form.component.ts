import { Component, OnInit } from '@angular/core';
import { AppService } from '../../services/app.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-employee-search-form',
  templateUrl: './employee-search-form.component.html',
  styleUrls: ['./employee-search-form.component.css']
})
export class EmployeeSearchFormComponent implements OnInit {

  employees: any;
  currentEmployee = null;
  employeeForm;

  constructor(
    private app_service: AppService,
    private formBuilder: FormBuilder) {
    this.employeeForm = this.formBuilder.group({
        name: '',
        last_name: '',
        email: ''
      });
  }


  ngOnInit(): void {
    this.retrieveAllEmployees();
  }

  retrieveAllEmployees(): void {
      this.app_service.getAll()
        .subscribe(
          data => {
            this.employees = data;
            console.log(data);
          },
          error => {
            console.log(error);
          });
    }

  onSubmit(){
    this.app_service.getEmployeeByEmail(this.employeeForm.get('email').value)
      .subscribe(
        data => {
          this.employees = data;
          console.log(data);
        });
    this.employeeForm.reset();
  }

}
