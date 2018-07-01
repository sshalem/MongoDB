import { MatTableModule, MatSortModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';


import { AdminService } from './admin.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { GetAllCompaniesComponent } from './components/get-all-companies/get-all-companies.component';
import { GetCompanyByIdComponent } from './components/get-company-by-id/get-company-by-id.component';
import { GetCompanyByNameComponent } from './components/get-company-by-name/get-company-by-name.component';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { UpdateCompanyComponent } from './components/update-company/update-company.component';
import { DeleteCompanyComponent } from './components/delete-company/delete-company.component';
import { DeleteCustomerComponent } from './components/delete-customer/delete-customer.component';
import { CreateCustomerComponent } from './components/create-customer/create-customer.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { GetAllCustomersComponent } from './components/get-all-customers/get-all-customers.component';
import { GetCustomerByIdComponent } from './components/get-customer-by-id/get-customer-by-id.component';
import { GetCustomerByNameComponent } from './components/get-customer-by-name/get-customer-by-name.component';


@NgModule({
  declarations: [
    AppComponent,
    GetAllCompaniesComponent,
    GetCompanyByIdComponent,
    GetCompanyByNameComponent,
    CreateCompanyComponent,
    UpdateCompanyComponent,
    DeleteCompanyComponent,
    DeleteCustomerComponent,
    CreateCustomerComponent,
    UpdateCustomerComponent,
    GetAllCustomersComponent,
    GetCustomerByIdComponent,
    GetCustomerByNameComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MatTableModule, 
    MatSortModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    RouterModule.forRoot([
      {
        path : 'createCompany',
        component : CreateCompanyComponent
      },
      {
        path : 'updateCompany',
        component : UpdateCompanyComponent
      },
      {
        path : 'deleteCompany',
        component : DeleteCompanyComponent
      },
      {
        path: 'getAllCompanies',
        component : GetAllCompaniesComponent
      },
      {
        path : 'getCompanyById',
        component : GetCompanyByIdComponent
      },
      {
        path : 'getCompanyByName',
        component : GetCompanyByNameComponent
      },
      {
        path : 'createCustomer',
        component : CreateCustomerComponent
      },
      {
        path : 'updateCustomer',
        component : UpdateCustomerComponent
      },
      {
        path: 'deleteCustomer',
        component : DeleteCustomerComponent
      },
      {
        path : 'getAllCustomers',
        component : GetAllCustomersComponent
      },
      {
        path : 'getCustomerById',
        component : GetCustomerByIdComponent
      },
      {
        path : 'getCustomerByName',
        component : GetCustomerByNameComponent
      },
    ])
  ],
  providers: [AdminService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
