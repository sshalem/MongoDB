import swal from 'sweetalert2';
import { Coupon } from './../common/Coupon';
import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';



@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {

  public _coupon : Coupon = new Coupon();

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public purchaseCoupon()
  {
    this._http.post('http://localhost:8080/customerws/purchasecoupon', this._coupon)
    .subscribe(function(res)
      {
        console.log(res);
        swal({
          title: 'coaupon purchased',      
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

}
