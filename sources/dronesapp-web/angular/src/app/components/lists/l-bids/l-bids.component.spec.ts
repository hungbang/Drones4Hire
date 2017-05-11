import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LBidsComponent } from './l-bids.component';

describe('LProposalsComponent', () => {
  let component: LBidsComponent;
  let fixture: ComponentFixture<LBidsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LBidsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LBidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
