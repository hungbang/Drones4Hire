import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FBidComponent } from './f-bid.component';

describe('FBidComponent', () => {
  let component: FBidComponent;
  let fixture: ComponentFixture<FBidComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FBidComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FBidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
