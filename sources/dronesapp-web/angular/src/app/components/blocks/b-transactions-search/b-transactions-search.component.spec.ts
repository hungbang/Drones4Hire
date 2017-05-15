import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BTransactionsSearchComponent } from './b-transactions-search.component';

describe('BTransactionsSearchComponent', () => {
  let component: BTransactionsSearchComponent;
  let fixture: ComponentFixture<BTransactionsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BTransactionsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BTransactionsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
