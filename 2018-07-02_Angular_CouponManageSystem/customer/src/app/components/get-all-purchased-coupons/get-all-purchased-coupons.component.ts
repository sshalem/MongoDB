import { CustomerService } from './../../customer.service';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import swal from 'sweetalert2';


import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-all-purchased-coupons',
  templateUrl: './get-all-purchased-coupons.component.html',
  styleUrls: ['./get-all-purchased-coupons.component.css']
})
export class GetAllPurchasedCouponsComponent implements OnInit {
  
  public _noResults : boolean;
  public _coupon : Coupon [] = [];
  public dataSource : MatTableDataSource<Coupon>;  
  public displayedColumns = ["id", "title", "startDate", "endDate",
                             "amount", "type", "price","message", "image", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator; 

  constructor(private _http : Http, private _customerService : CustomerService) { }

  ngOnInit() {
    this.getPurchasedCoupons();
  }

  public getPurchasedCoupons()
  { 
    this._noResults = false; 
    this._customerService.getPurchasedCoupons().subscribe(coupons =>
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
