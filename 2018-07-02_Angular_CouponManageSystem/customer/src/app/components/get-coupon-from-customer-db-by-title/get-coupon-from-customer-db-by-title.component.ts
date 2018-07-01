import { CustomerService } from './../../customer.service';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-coupon-from-customer-db-by-title',
  templateUrl: './get-coupon-from-customer-db-by-title.component.html',
  styleUrls: ['./get-coupon-from-customer-db-by-title.component.css']
})
export class GetCouponFromCustomerDbByTitleComponent implements OnInit {

  public _title : string;
  public _coupon : Coupon = new Coupon();

  constructor(private _http : Http, private _customerService : CustomerService) { }

  ngOnInit() {
  }

  public getPurchasedCouponsByTitle()
  {
    var self = this;
    if(this._title == null || this._title.trim().length == 0)
    {
      swal({
        title: 'enter a valid name',      
        type: 'warning'
      })
    }
    else
    {      
      this._customerService.getPurchasedCouponsByTitle(this._title).subscribe(function(Coupon)
        {
          console.log(Coupon);
          self._coupon = Coupon;
         },function(err)
         {
           console.log(err);
           swal({
             title: err._body,      
             type: 'error'
           })         
      })      
    }
    
  }
}
