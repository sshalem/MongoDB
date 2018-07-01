import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCouponFromCustomerDbByTitleComponent } from './get-coupon-from-customer-db-by-title.component';

describe('GetCouponFromCustomerDbByTitleComponent', () => {
  let component: GetCouponFromCustomerDbByTitleComponent;
  let fixture: ComponentFixture<GetCouponFromCustomerDbByTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCouponFromCustomerDbByTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCouponFromCustomerDbByTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
