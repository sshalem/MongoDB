import swal from 'sweetalert2';
import { Customer } from './../common/Customer';
import { Http } from '@angular/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AdminService } from './../../admin.service';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';

import 'rxjs/add/operator/map'; // chinese 

@Component({
  selector: 'app-get-all-customers',
  templateUrl: './get-all-customers.component.html',
  styleUrls: ['./get-all-customers.component.css']
})
export class GetAllCustomersComponent implements OnInit {

  public _noResults : boolean;
  public _customer : Customer [] =[
  ];

  public dataSource : MatTableDataSource<Customer>;  
  public displayedColumns = ["id", "custName", "password", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator;


  constructor(private _http: Http, private adminService: AdminService) {
    this._noResults = true;  
   }

  ngOnInit() {   
  }


  public getAllCustomers()
  {       
    this._noResults = false;
    this.adminService.getAllCustomers().subscribe(customers=>
    {
      console.log(customers);
      if(customers == 0)
      {
        this._noResults = true;              
      }
      this.dataSource = new MatTableDataSource(customers);            
      this.dataSource.sort = this.sort;
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }


  public deleteCustomer(_id :number) {  
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
        this._http.delete('http://localhost:8080/adminws/customer/delete/' + _id)
        .subscribe(function(response)
        {        
          console.log(response); 
          self.getAllCustomers();
        })      
        swal(
          'Deleted!',
          'Customer has been deleted.',
          'success'          
        )
      }
    }) 
  }

  public editCustomer(cust : Customer ) {     
    this.adminService.customerServ = cust;    
  }  

}
