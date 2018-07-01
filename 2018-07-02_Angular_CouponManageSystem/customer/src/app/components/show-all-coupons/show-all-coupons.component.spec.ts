import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowAllCouponsComponent } from './show-all-coupons.component';

describe('ShowAllCouponsComponent', () => {
  let component: ShowAllCouponsComponent;
  let fixture: ComponentFixture<ShowAllCouponsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowAllCouponsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowAllCouponsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
