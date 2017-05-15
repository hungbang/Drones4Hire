import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LTransactionsSearchComponent } from './l-transactions-search.component';

describe('LTransactionsSearchComponent', () => {
  let component: LTransactionsSearchComponent;
  let fixture: ComponentFixture<LTransactionsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LTransactionsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LTransactionsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
