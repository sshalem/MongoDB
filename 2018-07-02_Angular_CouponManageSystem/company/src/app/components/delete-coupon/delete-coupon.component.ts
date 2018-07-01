import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-coupon',
  templateUrl: './delete-coupon.component.html',
  styleUrls: ['./delete-coupon.component.css']
})
export class DeleteCouponComponent implements OnInit {

  public _id : number;
  public _coupon : Coupon = new Coupon();
  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public deleteCoupon()
  {
    this._http.delete('http://localhost:8080/companyws/deletecoupon/' + this._id)
    .subscribe(function(response)
    {
      console.log(response);
    })
  }
}
