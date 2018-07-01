import { CustomerService } from './../../customer.service';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import swal from 'sweetalert2';

import 'rxjs/add/operator/map';

@Component({
  selector: 'app-show-all-coupons',
  templateUrl: './show-all-coupons.component.html',
  styleUrls: ['./show-all-coupons.component.css']
})
export class ShowAllCouponsComponent implements OnInit {

  public _noResults : boolean;
  public _coupon : Coupon [] = [];
  public dataSource : MatTableDataSource<Coupon>;  
  public displayedColumns = ["id", "title", "startDate", "endDate",
                             "amount", "type", "price","message", "image", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator; 


  constructor(private _http : Http, private _customerService : CustomerService) { 
    this._noResults = true;
    this.showCoupons();    
  }

  ngOnInit() {    
  }

  public showCoupons()
  {
    this._noResults = false;    
    this._customerService.showAllCoupons().subscribe(coupons =>
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

  public purchaseCoupon(coupon : Coupon)
  {   
    this._customerService.purchaseCoupon(coupon).subscribe(res =>
      {
        console.log(res);
        swal({
          title: 'coaupon purchased',      
          type: 'success'
        })
        this.showCoupons();
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
