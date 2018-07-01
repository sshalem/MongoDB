import { AdminService } from './../../admin.service';
import { Http } from '@angular/http';
import { Company } from './../common/Company';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-get-company-by-id',
  templateUrl: './get-company-by-id.component.html',
  styleUrls: ['./get-company-by-id.component.css']
})
export class GetCompanyByIdComponent implements OnInit {

  public _id : number;
  public _company : Company = new Company();
  
  constructor(private _http : Http, private adminService : AdminService) { }  

  ngOnInit() {
  }

  public getCompanyById()
  {
    var self = this;
    if(this._id == null)
    {
      swal({                       
        title: 'enter Id then press "Get Company"',
        type: 'info'                  
      }) 
    }
    else
    {
     this.adminService.getCompanyById(this._id)
     .subscribe(function(response)
      {                 
         console.log(response);            
         self._company = response;
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
    var self =this;
    if(_id == null){
      swal({
        title: 'enter Id then press "Get Company"',      
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
        self._id = null;
      }
    })
    }                  
  }

  public editCompany(comp : Company )
  {           
    this.adminService.compServ = comp;
  }  

}
