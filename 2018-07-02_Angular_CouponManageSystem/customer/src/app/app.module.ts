import { CustomerService } from './customer.service';
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
import { GetAllPurchasedCouponsComponent } from './components/get-all-purchased-coupons/get-all-purchased-coupons.component';
import { GetAllPurchasedCouponsByPriceComponent } from './components/get-all-purchased-coupons-by-price/get-all-purchased-coupons-by-price.component';
import { GetAllPurchasedCouponsByTypeComponent } from './components/get-all-purchased-coupons-by-type/get-all-purchased-coupons-by-type.component';
import { GetCouponFromCustomerDbByTitleComponent } from './components/get-coupon-from-customer-db-by-title/get-coupon-from-customer-db-by-title.component';
import { PurchaseCouponComponent } from './components/purchase-coupon/purchase-coupon.component';
import { ShowAllCouponsComponent } from './components/show-all-coupons/show-all-coupons.component';


@NgModule({
  declarations: [
    AppComponent,
    GetAllPurchasedCouponsComponent,
    GetAllPurchasedCouponsByPriceComponent,
    GetAllPurchasedCouponsByTypeComponent,
    GetCouponFromCustomerDbByTitleComponent,
    PurchaseCouponComponent,
    ShowAllCouponsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatSortModule,
    RouterModule.forRoot([
      { 
        path : 'purchaseCoupons',
        component : PurchaseCouponComponent
      },
      { 
        path : 'showAllCoupons',
        component : ShowAllCouponsComponent
      },
      {
        path : 'getAllPurchasedCoupons',
        component : GetAllPurchasedCouponsComponent
      },
      {
        path : 'getAllPurchasedCouponsByType',
        component : GetAllPurchasedCouponsByTypeComponent
      },
      {
        path : 'getAllPurchasedCouponsByPrice',
        component : GetAllPurchasedCouponsByPriceComponent
      },
      {
        path : 'getAllPurchasedCouponsByTitle',
        component : GetCouponFromCustomerDbByTitleComponent
      },
    ])    
  ],
  providers: [CustomerService,{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
