import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BTransactionsComponent } from './b-transactions.component';

describe('BTransactionsComponent', () => {
  let component: BTransactionsComponent;
  let fixture: ComponentFixture<BTransactionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BTransactionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BTransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
