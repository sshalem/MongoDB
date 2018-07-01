import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCustomerByNameComponent } from './get-customer-by-name.component';

describe('GetCustomerByNameComponent', () => {
  let component: GetCustomerByNameComponent;
  let fixture: ComponentFixture<GetCustomerByNameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCustomerByNameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCustomerByNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
