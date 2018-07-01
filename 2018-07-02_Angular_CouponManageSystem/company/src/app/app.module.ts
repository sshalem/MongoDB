import { CompanyService } from './company.service';
import { MatTableModule, MatSortModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { HashLocationStrategy, LocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { CreateCouponComponent } from './components/create-coupon/create-coupon.component';
import { UpdateCouponComponent } from './components/update-coupon/update-coupon.component';
import { DeleteCouponComponent } from './components/delete-coupon/delete-coupon.component';
import { GetAllCouponsComponent } from './components/get-all-coupons/get-all-coupons.component';
import { GetCouponByIdComponent } from './components/get-coupon-by-id/get-coupon-by-id.component';
import { GetCouponByNameComponent } from './components/get-coupon-by-name/get-coupon-by-name.component';
import { GetCouponsByDateComponent } from './components/get-coupons-by-date/get-coupons-by-date.component';
import { GetCouponsByPriceComponent } from './components/get-coupons-by-price/get-coupons-by-price.component';
import { GetCouponsByTypeComponent } from './components/get-coupons-by-type/get-coupons-by-type.component';



@NgModule({
  declarations: [
    AppComponent,
    CreateCouponComponent,
    UpdateCouponComponent,
    DeleteCouponComponent,
    GetAllCouponsComponent,
    GetCouponByIdComponent,
    GetCouponByNameComponent,
    GetCouponsByDateComponent,
    GetCouponsByPriceComponent,
    GetCouponsByTypeComponent  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MatTableModule, 
    MatSortModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    RouterModule.forRoot([
      {
        path : 'createCoupon',
        component : CreateCouponComponent
      },
      {
        path : 'updateCoupon',
        component : UpdateCouponComponent
      },
      {
        path : 'deleteCoupon',
        component : DeleteCouponComponent
      },
      {
        path: 'getAllCoupons',
        component : GetAllCouponsComponent
      },
      {
        path : 'getCouponById',
        component : GetCouponByIdComponent
      },
      {
        path : 'getCouponByName',
        component : GetCouponByNameComponent
      },
      {
        path : 'getCouponsByDate',
        component : GetCouponsByDateComponent
      },
      {
        path : 'getCouponsByPrice',
        component : GetCouponsByPriceComponent
      },
      {
        path: 'getCouponsByType',
        component : GetCouponsByTypeComponent
      },      
    ])
  ],
  providers: [CompanyService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
