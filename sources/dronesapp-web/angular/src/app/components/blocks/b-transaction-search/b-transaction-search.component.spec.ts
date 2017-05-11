import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BTransactionSearchComponent } from './b-transaction-search.component';

describe('BTransactionSearchComponent', () => {
  let component: BTransactionSearchComponent;
  let fixture: ComponentFixture<BTransactionSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BTransactionSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BTransactionSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
