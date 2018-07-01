import { CompanyService } from './../../company.service';
import swal from 'sweetalert2';
import { Coupon } from './../common/Coupon';
import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-create-coupon',
  templateUrl: './create-coupon.component.html',
  styleUrls: ['./create-coupon.component.css']
})
export class CreateCouponComponent implements OnInit {

  public resturants : string = "https://previews.123rf.com/images/hollygraphic/hollygraphic1510/hollygraphic151000026/46527749-restaurant-graphic-design-logo-template-vintage-insignia.jpg"
  public electricity : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSD1eaMeiCj2eW-u7ifxMDVnqm7a6Ee4b_4xTI5kxo3cjWAHA8_";
  public food : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS2m41_zg6vFA7Wg-jjO7GonTTGb8bGMXbSHMnvRGJwh7gpj40ehg";
  public health : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTGYLRATx-Vwc9P3N0JjVCGWNyMKdzxGmiBkXBfGLv6M06_yNV8";
  public sports : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQHGK7vDFcaxeQ0V2lfQlA9oX4_C1QcRPoalrmL9TY2ksdyA2Sj2w";
  public camping : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRPKu0mG_0QNQf5mY8pn4gyvIS3cppw9qG941HR5zmgWkVPRIvNsg";
  public travelling : string = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwSbCuQNrEDkhimZ9XsSEFp4qQGcofJdsPDqI2cGtqLeQvcD4NjA";

  public images : string [] = [];
  public _coupon : Coupon = new Coupon();


  constructor(private _http : Http, private _companyService : CompanyService) {    
   }

  ngOnInit() {
  }

  public addCoupon()
  {  
    if( this._coupon.startDate === this._coupon.endDate ||
        this._coupon.startDate > this._coupon.endDate) 
      {
        swal({
          title: 'endDate must be after startDate!',      
          type: 'warning'
        }) 
      }
      else
      {
        this._companyService.addCoupon(this._coupon);
      }     
  }
}
  
