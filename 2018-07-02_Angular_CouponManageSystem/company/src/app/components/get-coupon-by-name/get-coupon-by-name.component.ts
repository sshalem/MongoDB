import { CompanyService } from './../../company.service';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-coupon-by-name',
  templateUrl: './get-coupon-by-name.component.html',
  styleUrls: ['./get-coupon-by-name.component.css']
})
export class GetCouponByNameComponent implements OnInit {

  public _name : string;
  public _coupon : Coupon = new Coupon();
  constructor(private _http : Http, private _companyService : CompanyService) { }

  ngOnInit() {
  }


  public getCouponByName()
  {    
    if(this._name == null || this._name.trim().length == 0)
    {
      swal({
        title: 'enter a valid name then press "Get Coupon by Name',      
        type: 'warning'
      })
    }
    else{
      this._companyService.getCouponByName(this._name)
      .subscribe(Coupon =>
       {
         console.log(Coupon);
         this._coupon = Coupon;
        },function(err){
          console.log(err);
           swal({
              title: err._body,      
              type: 'error'
            }) 
        })          
    }   
  }


  public deleteCoupon(id : number)
  {
    var self = this;
    if(id == null)
    {
      swal({
        title: 'enter Name then press "Get Coupon By Name"',      
         type: 'error'
          }) 
    }    
    else
    {
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
          this._http.delete('http://localhost:8080/companyws/deletecoupon/' + id).subscribe(function(response)
          {        
            console.log(response);             
          })      
          swal(
            'Deleted!',
            'Coupon has been deleted.',
            'success'          
          )
          self._coupon = this._companyService.coup;          
        }
      }); 
    }       
  }

  public editCoupon(couponNew :Coupon)
  {    
    this._companyService._coupon = couponNew;    
  }

}
