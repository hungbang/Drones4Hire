import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PrBindComponent } from './pr-bid.component';

describe('PreviewProposalComponent', () => {
  let component: PrBindComponent;
  let fixture: ComponentFixture<PrBindComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PrBindComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PrBindComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
