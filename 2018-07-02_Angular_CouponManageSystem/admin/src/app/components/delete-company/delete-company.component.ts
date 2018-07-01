import { Http } from '@angular/http';
import { Company } from './../common/Company';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-company',
  templateUrl: './delete-company.component.html',
  styleUrls: ['./delete-company.component.css']
})
export class DeleteCompanyComponent implements OnInit {

  public _id : number;
  public _company : Company = new Company();
 
  // dependency injection for http object -> will become
  // a member of this class

  constructor(private _http : Http) { }

  ngOnInit() {
  }

  public deleteCompany()
  {
    this._http.delete('http://localhost:8080/adminws/company/delete/' + this._id)
              .subscribe(function(response)
              {
                console.log(response);
              });
  }


}


