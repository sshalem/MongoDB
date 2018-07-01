import { Customer } from './../common/Customer';
import { Http } from '@angular/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-customer',
  templateUrl: './delete-customer.component.html',
  styleUrls: ['./delete-customer.component.css']
})
export class DeleteCustomerComponent implements OnInit {

  public _id : number;
  public _customer : Customer = new Customer();

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public deleteCustomer(_id : number)
  {
    this._http.delete('http://localhost:8080/adminws/customer/delete/' + this._id)
    .subscribe(function(res)
      {
        console.log(res);
      })
  }
}
