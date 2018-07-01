import swal from 'sweetalert2';
import { Http } from '@angular/http';
import { Customer } from './../common/Customer';
import { Component, OnInit } from '@angular/core';
import { AdminService } from './../../admin.service';


@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {

  public _customer : Customer = new Customer();


  constructor(private _http : Http, private adminService: AdminService) { 
    this._customer = this.adminService.customerServ;
  }

  ngOnInit() {
  }

  public updateCustomer()
  {
    if(this._customer.custName == null || this._customer.custName.trim().length == 0 ||
       this._customer.password == null || this._customer.password.trim().length == 0 )
    {
      swal({
        title: 'Empty fields are not allowed !!! make sure you fill the fields next time',  
        text : '',          
        type: 'warning'
       }) 
    }
    else{
      this.adminService.updateCustomer(this._customer);
    }
    
  }
  
}
