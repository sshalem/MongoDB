import swal from 'sweetalert2';
import { Coupon } from './components/common/Coupon';
import { Http } from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';


@Injectable()
export class CustomerService {

  constructor(private _http : Http) { }

  public showAllCoupons(){
    return this._http.get('http://localhost:8080/customerws/showallcoupons')
    .map(function(response)
      {
        return (response).json();
      })
  }

public purchaseCoupon(coupon : Coupon)
  {
    return this._http.post('http://localhost:8080/customerws/purchasecoupon', coupon);    
  }

  // public purchaseCoupon(coupon : Coupon)
  // {
  //   this._http.post('http://localhost:8080/customerws/purchasecoupon', coupon)
  //   .subscribe(function(res)
  //     {
  //       console.log(res);
  //       swal({
  //         title: 'coaupon purchased',      
  //         type: 'success'
  //       })
  //     },function(err)
  //       {
  //         console.log(err);
  //         swal({
  //           title: err._body,      
  //           type: 'error'
  //         })
  //       })
  // }

  // get purchased coupons
  public getPurchasedCoupons()
  {     
    return this._http.get('http://localhost:8080/customerws/getpurchsedcoupons')
    .map(function(res)
      {
        return (res).json();
      })  
  }

  // get purchased coupons by price
  public getPurchasedCouponsByPrice(minPrice:number, maxPrice : number){
    return this._http.get('http://localhost:8080/customerws/getpurchsedcoupons/byprice/' + minPrice + '/' + maxPrice)
    .map(function(res)
      {
        return (res).json();
      })
  }


  // get purchased coupons by title
  public getPurchasedCouponsByTitle(title: string){
    return this._http.get('http://localhost:8080/customerws/getpurchsedcoupons/bytitle/' + title)
    .map(function(res)
      {
        return (res).json();
      })
  }

}
