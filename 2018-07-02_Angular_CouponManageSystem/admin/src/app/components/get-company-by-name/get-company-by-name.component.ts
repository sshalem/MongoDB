import swal from 'sweetalert2';
import { AdminService } from './../../admin.service';
import { Http } from '@angular/http';
import { Company } from './../common/Company';
import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-company-by-name',
  templateUrl: './get-company-by-name.component.html',
  styleUrls: ['./get-company-by-name.component.css']
})
export class GetCompanyByNameComponent implements OnInit {

  public _companyName : string;
  public _company : Company = new Company();

  constructor(private _http : Http, private adminService : AdminService) { }

  ngOnInit() {    
  }

  public getCompanyByName()
  {    
    if(this._companyName == null || this._companyName.trim().length == 0)
    {
      swal({                       
        title: 'please enter Name',
        type: 'info'                  
          }) 
    }
    else
    {
      this.adminService.getCompanyByName(this._companyName)
      .subscribe(response =>
      {                 
        console.log(response);            
        this._company = response;
      },
      function(err)
      {
        console.error(err);
        swal({
          title: err._body,      
          type: 'error'
            }) 
      });
    }    
  }
  
  public deleteCompany(_id :number)
  {  
    var self = this;
    if(_id == null){
      swal({
        title: 'enter Company name, and press "Get Company" ',      
         type: 'error'
          }) 
    }
    else{      
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
        .subscribe(function(response)
        {        
          console.log(response);           
        })      
        swal(
          'Deleted!',
          'Company has been deleted.',
          'success'          
        )
        self._company = this.adminService.comp;
        self._companyName = null;
      }
    })
    } 
  }

  public editCompany(comp : Company )
  {     
    this.adminService.compServ = comp;    
  }  

}
