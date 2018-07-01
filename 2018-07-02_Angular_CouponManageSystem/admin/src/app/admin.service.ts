import swal from 'sweetalert2';
import { Http } from '@angular/http';
import { Customer } from './components/common/Customer';
import { Company } from './components/common/Company';
import { Injectable } from '@angular/core';

import 'rxjs/add/operator/map';

@Injectable()
export class AdminService {

  private getAllCompaniesUrl = "http://localhost:8080/adminws/company";
  
  public comp : Company = new Company();
  public cust : Customer = new Customer();
  public compServ : Company;
  public customerServ : Customer;

  constructor(private _http: Http) {}
  
  //getAllCompanies()
  public getAllCompanies() {
    return this._http.get(this.getAllCompaniesUrl).map(function(companyResponse)
    {
      return (companyResponse).json();
    })   
  }

  // create Company
  public addCompany(company : Company)
  {
    this._http.post('http://localhost:8080/adminws/company', company)
        .subscribe(function(response)
              { 
              console.log(response);
                swal({
                     title: 'Company Created successfully!',      
                     type: 'success'
                    })                                                  
              },function(err)
                {
                  console.log(err);
                    swal({
                      title: err._body,      
                      type: 'error'
                    }) 
                })
  }

  // Update Company
  public updateCompany(_company : Company){
    this._http.put('http://localhost:8080/adminws/company/update/' + _company.id , _company)
      .subscribe(
        function(response)
            { 
            console.log(response);
              swal({
                   title: 'Company Updated successfully!',      
                   type: 'success'
                  }) ;                                                                                           
            },function(err)
              {
                console.log(err);
                if(err)
                {
                  swal({
                    title: 'Update Failed!',      
                    type: 'error'
                  }) ;                  
                }
              }
        ) 
  }

  // get company By Id
  public getCompanyById(_id : Number){
    return this._http.get('http://localhost:8080/adminws/company/id/' + _id )
    .map(function(companyResponse)
        {     
          return (companyResponse).json();
        })
  }
  
  // get company By Name
  public getCompanyByName(_companyName : string){
    return this._http.get('http://localhost:8080/adminws/company/name/' + _companyName )
      .map(function(companyResponse)
        {     
          return (companyResponse).json();
        })
  }
 

// -------------------------------------------------------------
// -------------------------------------------------------------
// -------------------------------------------------------------


  //getAllCustomers()
  public getAllCustomers(){
    return this._http.get('http://localhost:8080/adminws/customer')
    .map(function(customerResponse)
    {
      return (customerResponse).json();
    })
  }

  // create Customer
  public addCustomer(customer : Customer) {
    this._http.post('http://localhost:8080/adminws/customer', customer)
        .subscribe(function(res)
        { 
          console.log(res);
            swal({
                 title: 'Customer Created successfully!',      
                 type: 'success'
                })                                                  
          },function(err)
            {
              console.log(err);              
                swal({
                  title: err._body,      
                  type: 'error'
                })               
            }) 
  }

  // update Customer
  public updateCustomer(_customer){
    this._http.put('http://localhost:8080/adminws/customer/update/' + _customer.id , _customer)
      .subscribe(function(res)
      { 
        console.log(res);
          swal({
               title: 'Company Updated successfully!',      
               type: 'success'
              })                                                  
        },function(err)
          {
            console.log(err);
            if(err)
            {
              swal({
                title: 'Update Failed!',      
                type: 'error'
              }) 
            }
          })
  }

  // get Customer By Id
  public getCustomerById(_id : Number){
    return this._http.get('http://localhost:8080/adminws/customer/id/' + _id)
    .map(function(customerResponse)
        {     
          return (customerResponse).json();
        })
  }
  
  // get Customer By Name
  public getCustomerByName(_custName : string){
    return this._http.get('http://localhost:8080//adminws/customer/name/'+ _custName)
    .map(function(customerResponse)
      {
        return (customerResponse).json();
      })
  }

  // delete Customer
  public deleteCustomer(_id : Number){
    swal({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {      
      if (result.value) {           
        this._http.delete('http://localhost:8080/adminws/customer/delete/' + _id)
        .subscribe(function(response)
        {        
          console.log(response);           
        })      
        swal(
          'Deleted!',
          'Customer has been deleted.',
          'success'          
        )
      }
    })   
  }
}
