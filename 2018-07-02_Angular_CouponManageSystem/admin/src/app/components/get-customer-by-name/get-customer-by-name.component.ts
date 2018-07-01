import { Customer } from './../common/Customer';
import { Http } from '@angular/http';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
import { AdminService } from './../../admin.service';
import 'rxjs/add/operator/map';



@Component({
  selector: 'app-get-customer-by-name',
  templateUrl: './get-customer-by-name.component.html',
  styleUrls: ['./get-customer-by-name.component.css']
})
export class GetCustomerByNameComponent implements OnInit {

  public _custName : string;
  public _customer : Customer = new Customer();

  constructor(private _http: Http, private adminService: AdminService) { }

  ngOnInit() {
  }

  public getCustomerByName()
  {
    var self = this;
    if(this._custName == null || this._custName.trim().length == 0)
    {
      swal({
        title: 'enter customer name',      
        type: 'info'
      })
    }
    else
    {
      this.adminService.getCustomerByName(this._custName).subscribe(
        function(response)
          {
            console.log(response);
            self._customer = response;
          },function(err){
            console.error(err);
            swal({
              title: err._body,      
              type: 'error'
            }) 
          })        
    }    
  }


  public editCustomer(cust : Customer )  {     
    this.adminService.customerServ = cust;    
  }  

  public deleteCustomer(_id :number)
  {  
    var self = this;
    if(_id == null){
      swal({
        title: 'enter Customer name, and press "Get Customer" ',      
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
        this._http.delete('http://localhost:8080/adminws/customer/delete/' + _id)
        .subscribe(function(response)
        {        
          console.log(response);           
        })      
        swal(
          'Deleted!',
          'Customer has been deleted.',
          'success'          
        )
        self._customer = this.adminService.cust;
        self._custName = null;
      }
    })
    }    
  }

}
