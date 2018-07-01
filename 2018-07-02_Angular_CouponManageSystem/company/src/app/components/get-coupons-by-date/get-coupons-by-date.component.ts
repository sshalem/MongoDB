import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import { CompanyService } from './../../company.service';
import { Http } from '@angular/http';
import { Coupon } from './../common/Coupon';
import { Component, OnInit , ViewChild} from '@angular/core';
import swal from 'sweetalert2';
import 'rxjs/add/operator/map';


@Component({
  selector: 'app-get-coupons-by-date',
  templateUrl: './get-coupons-by-date.component.html',
  styleUrls: ['./get-coupons-by-date.component.css']
})
export class GetCouponsByDateComponent implements OnInit {

  public _startDate : Date;
  public _endDate : Date;

  public dataSource : MatTableDataSource<Coupon>;  
  public displayedColumns = ["id", "title", "startDate", "endDate",
                             "amount", "type", "price","message", "image", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator; 

  public _noResults : boolean;
  public _coupon : Coupon [] = [
  ];

  constructor(private _http : Http, private _companyService : CompanyService) {
    this._noResults = true;
   }

  ngOnInit() {    
  }

  public getCouponsByDate()
  { 
    
    if(this._startDate == null || this._endDate == null)
    {
      swal({
        title: 'start/end date missing',      
        type: 'warning'
      })
    } else if(this._startDate === this._endDate || this._startDate > this._endDate)
    {
      swal({
        title: 'endDate must be after startDate!',      
        type: 'warning'
      })
    }
    else{
      this._noResults = false;
      this._companyService.getCouponsBetweenDates(this._startDate, this._endDate).subscribe(coupons =>
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
  }

   // this method is for filter data from table
   applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  public deleteCoupon(_id : number)
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
        this._http.delete('http://localhost:8080/companyws/deletecoupon/' + _id).subscribe(function(response)
      {        
        console.log(response); 
        self.getCouponsByDate();
      });
        swal(
          'Deleted!',
          'Your file has been deleted.',
          'success'
        )
      }
    })
  }

  public editCoupon(couponNew :Coupon)
  {    
    this._companyService._coupon = couponNew;    
  }
  
}
