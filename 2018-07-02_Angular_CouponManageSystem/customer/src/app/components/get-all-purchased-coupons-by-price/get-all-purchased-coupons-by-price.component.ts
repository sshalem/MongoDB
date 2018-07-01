import { CustomerService } from './../../customer.service';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import swal from 'sweetalert2';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-all-purchased-coupons-by-price',
  templateUrl: './get-all-purchased-coupons-by-price.component.html',
  styleUrls: ['./get-all-purchased-coupons-by-price.component.css']
})
export class GetAllPurchasedCouponsByPriceComponent implements OnInit {

  public _noResults : boolean;
  public _minPrice : number;
  public _maxPrice : number;
  
  public dataSource : MatTableDataSource<Coupon>;  
  public displayedColumns = ["id", "title", "startDate", "endDate",
                             "amount", "type", "price","message", "image", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator;  

  
  public _coupon : Coupon [] = [];

  constructor(private _http : Http,  private _customerService : CustomerService) {
    this._noResults = true;
   }

  ngOnInit() {
  }

  public getPurchasedCouponsByPrice()
  { 
    if(this._minPrice >= this._maxPrice)
    {
    swal({
      title: 'maximum price must be bigger than minimum price',      
      type: 'warning'
    }) 
    }    
    this._noResults = false;     
    this._customerService.getPurchasedCouponsByPrice(this._minPrice, this._maxPrice).subscribe(coupons =>
        {          
          console.log(coupons) ;
          if(coupons == 0)
          {
            this._noResults = true;
          }
          this.dataSource = new MatTableDataSource(coupons);            
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;         
        })
  }

  // this method is for filter data from table
  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

}
