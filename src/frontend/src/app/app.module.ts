import { BrowserModule } from '@angular/platform-browser'
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { AppService } from './services/app.service';
import { EmployeeSearchFormComponent } from './components/employee-search-form/employee-search-form.component';
import { EmployeesTableComponent } from './components/employees-table/employees-table.component'
import { TableSortableHeader } from './components/employees-table/employees-table.component'

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    EmployeeSearchFormComponent,
    EmployeesTableComponent,
    TableSortableHeader,
    EmployeesTableComponent
  ],
  imports: [
    AppRoutingModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }
