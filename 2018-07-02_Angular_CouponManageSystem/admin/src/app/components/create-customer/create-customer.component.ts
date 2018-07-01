import { AdminService } from './../../admin.service';
import swal from 'sweetalert2';
import { Http } from '@angular/http';
import { Customer } from './../common/Customer';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-create-customer',
  templateUrl: './create-customer.component.html',
  styleUrls: ['./create-customer.component.css']
})
export class CreateCustomerComponent implements OnInit {

  public customer : Customer = new Customer();
  constructor(private _http : Http, private _adminServeic : AdminService) { }

  ngOnInit() {
  }

  public addCustomer()  {
    this._adminServeic.addCustomer(this.customer);
  }

}
