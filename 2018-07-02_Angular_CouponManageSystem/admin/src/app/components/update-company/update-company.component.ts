import swal from 'sweetalert2';
import { AdminService } from './../../admin.service';
import { Company } from './../common/Company';
import { Component, OnInit , Input} from '@angular/core';
import { Http } from '@angular/http';


@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css']
})
export class UpdateCompanyComponent implements OnInit {
  
  public _company : Company = new Company();
 
  constructor(private _http : Http, private _adminService : AdminService) {      
      this._company = this._adminService.compServ;    
   }

  ngOnInit() {
    
  }

  public updateCompany()  
  {     
    if(this._company.compName == null || this._company.compName.trim().length == 0 ||
       this._company.password == null || this._company.password.trim().length == 0 ||
       this._company.email == null || this._company.email.trim().length == 0)
    {
      swal({
        title: 'Empty fields are not allowed !!! make sure you fill the fields next time',  
        text : '',          
        type: 'warning'
       }) 
    }
    else
    {
      this._adminService.updateCompany(this._company);
    }    
  }
}
