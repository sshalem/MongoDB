import { CompanyService } from './../../company.service';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import { Coupon } from './../common/Coupon';
import { Http } from '@angular/http';
import { Component, OnInit , ViewChild} from '@angular/core';
import swal from 'sweetalert2';


import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-coupons-by-type',
  templateUrl: './get-coupons-by-type.component.html',
  styleUrls: ['./get-coupons-by-type.component.css']
})
export class GetCouponsByTypeComponent implements OnInit {

  public dataSource : MatTableDataSource<Coupon>;  
  public displayedColumns = ["id", "title", "startDate", "endDate",
                             "amount", "type", "price","message", "image", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator; 

  public _noResults : boolean;
  public _type : string;
  public _coupon : Coupon [] = [
  ];

  constructor(private _http : Http, private _companyService : CompanyService) {
    this._noResults = true;
   }

  ngOnInit() {
  }

  public getCouponsByType()
  {
    this._noResults = false;
    this._companyService.getCouponsByType(this._type).subscribe(coupons =>
      {          
        console.log(coupons);
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
  applyFilter(filterValue: string) 
  {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }


  public deleteCoupon(id : number)
  {
    var self = this;
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
              self.getCouponsByType();
            })
        swal(
          'Deleted!',
          'Coupon has been deleted.',
          'success'          
        )
      }
    });    
  }


  public editCoupon(couponNew :Coupon)
  {    
    this._companyService._coupon = couponNew;    
  }

  
}
