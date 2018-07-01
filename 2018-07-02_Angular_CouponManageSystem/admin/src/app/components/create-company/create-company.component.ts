import { AdminService } from './../../admin.service';
import { Company } from './../common/Company';
import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import swal from 'sweetalert2';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {
  
  public company : Company = new Company();
 
  constructor(private _http : Http, private _adminService: AdminService) { }

  ngOnInit() {
  }
  
public addCompany()
  {
    this._adminService.addCompany(this.company);  
  }
    
}
