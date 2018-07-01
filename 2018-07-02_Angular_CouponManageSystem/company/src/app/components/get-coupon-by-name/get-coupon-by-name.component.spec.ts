import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCouponByNameComponent } from './get-coupon-by-name.component';

describe('GetCouponByNameComponent', () => {
  let component: GetCouponByNameComponent;
  let fixture: ComponentFixture<GetCouponByNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCouponByNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCouponByNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
