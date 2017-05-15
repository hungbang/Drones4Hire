import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FTransactionsSearchComponent } from './f-transactions-search.component';

describe('FTransactionsSearchComponent', () => {
  let component: FTransactionsSearchComponent;
  let fixture: ComponentFixture<FTransactionsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FTransactionsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FTransactionsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
