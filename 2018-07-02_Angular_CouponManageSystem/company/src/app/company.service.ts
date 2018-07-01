import swal from 'sweetalert2';
import { Coupon } from './components/common/Coupon';
import { Http } from '@angular/http';
import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';


@Injectable()
export class CompanyService {
 
  public coup : Coupon = new Coupon();
  public _coupon : Coupon;
  private getAllCouponsUrl = "http://localhost:8080/companyws/getallcoupon";

  constructor(private _http : Http) { }

  //add Coupon
  public addCoupon(coupon : Coupon){
    this._http.post('http://localhost:8080/companyws/createcoupon', coupon)
        .subscribe(function(response)
        { 
        console.log(response);
          swal({
               title: 'Coupon Created successfully!',      
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

  //get All Coupons()
  public getAllCoupons() {
    return this._http.get(this.getAllCouponsUrl).map(function(res)
    {
      return (res).json();
    })   
  }

  // get Coupons between dates
  public getCouponsBetweenDates(startDate : Date, endDate : Date){
    return this._http.get('http://localhost:8080/companyws/getallcoupon/betweendates/' + startDate + '/' + endDate)
      .map(function(res)
        {
          return (res).json();
        })
  }

  // get Coupons by price
  public getCouponsByPrice(_minPrice : number, _maxPrice : number){
    return this._http.get('http://localhost:8080/companyws/getallcoupon/byprice/' + _minPrice + '/' + _maxPrice)
    .map(function(res)
      {
        return (res).json();
      })
  }

  // get coupons by type
  public getCouponsByType(_type : string){
    return this._http.get('http://localhost:8080/companyws/getallcoupon/bytype/' + _type )
      .map(function(companyResponse)
      {     
        return (companyResponse).json();
      })
  }

  // update Coupon
  public updateCoupon(coupon : Coupon){
    this._http.put('http://localhost:8080/companyws/updatecoupon/' + coupon.id, coupon)
    .subscribe(function(res)
      {
        console.log(res);
        swal({
          title: 'coupon updated successfully ', 
          type: 'success'
         })          
      })
  }

  // get Coupon by Id
  public getCouponById(id : number){
    return this._http.get('http://localhost:8080/companyws/getallcoupon/byid/' + id)
    .map(function(couponRes) {
      return (couponRes).json();
      })
  }

  // get Coupon by name
  public getCouponByName(name : string){
    return this._http.get('http://localhost:8080/companyws/getallcoupon/byname/' + name)    
    .map(function(res) {
        return res.json();
      })
  }
}
