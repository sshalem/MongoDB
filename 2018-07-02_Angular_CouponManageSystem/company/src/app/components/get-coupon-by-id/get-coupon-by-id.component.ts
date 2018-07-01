import { CompanyService } from './../../company.service';
import { Http } from '@angular/http';
import { Coupon } from './../common/Coupon';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-coupon-by-id',
  templateUrl: './get-coupon-by-id.component.html',
  styleUrls: ['./get-coupon-by-id.component.css']
})
export class GetCouponByIdComponent implements OnInit {

  public _id : number;
  public _coupon : Coupon = new Coupon();
 
  constructor(private _http : Http, private _companyService : CompanyService) {}

  ngOnInit() {
  }

  public getCouponById()
  {
    var self = this;
    if(this._id == null)
    {
      swal({
        title: 'enter valid Id number then press "Get Coupon by Id" ',
        type: 'warning'
          })
    }
    else
    {
      this._companyService.getCouponById(this._id).subscribe(function(Coupon)
      {
        console.log(Coupon);
        self._coupon = Coupon;
      },
      function(err)
      {
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
        title: 'enter Id then press "Get Coupon By Id"',      
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
          self._id = 0;
        }
      }); 
    }       
  }

  public editCoupon(coup : Coupon)
  {
    this._companyService._coupon = coup;
  }

}
