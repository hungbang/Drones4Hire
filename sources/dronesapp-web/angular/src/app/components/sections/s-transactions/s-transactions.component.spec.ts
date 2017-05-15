import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { STransactionsComponent } from './s-transactions.component';

describe('STransactionsComponent', () => {
  let component: STransactionsComponent;
  let fixture: ComponentFixture<STransactionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ STransactionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(STransactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
