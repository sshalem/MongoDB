import swal from 'sweetalert2';
import { MatSort, MatSortable, MatTableDataSource, MatPaginator } from '@angular/material';
import { AdminService } from './../../admin.service';

import 'rxjs/add/operator/map';
import { Company } from './../common/Company';
import { Component, OnInit, ViewChild } from '@angular/core';

// since I declare Http in AdmiService no need to import
// it here or declare it in the constructor but since I'm still using it in the Delete function 
// I'm going to keep it here
import { Http } from '@angular/http';

@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})
export class GetAllCompaniesComponent implements OnInit {
  
  public _noResults : boolean;
  public _company : Company [] =[];
  public dataSource : MatTableDataSource<Company>;  
  public displayedColumns = ["id", "compName", "password", "email", "options"];

  @ViewChild(MatSort) sort: MatSort;  
  @ViewChild(MatPaginator) paginator: MatPaginator;  

  constructor(private _http: Http ,private _adminService: AdminService) { 
    this._noResults = true;      
  }
  
  ngOnInit() {         
  }

  public getAllCompanies() 
  {  
      this._noResults = false;
      this._adminService.getAllCompanies().subscribe(companies =>
          { 
            console.log(companies) ;
            if(companies == 0)
            {
              this._noResults = true;              
            }
            this.dataSource = new MatTableDataSource(companies);            
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

  public deleteCompany(_id :number)
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
        this._http.delete('http://localhost:8080/adminws/company/delete/' + _id)
        .subscribe(response =>
        {        
          console.log(response); 
          this.getAllCompanies();
        })      
        swal(
          'Deleted!',
          'Company has been deleted.',
          'success'          
        )
      }
    })   
  }

  public editCompany(comp : Company )
  {     
    this._adminService.compServ = comp; 
  }


}


