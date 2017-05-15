import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BBidsComponent } from './b-bids.component';

describe('BProposalsComponent', () => {
  let component: BBidsComponent;
  let fixture: ComponentFixture<BBidsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BBidsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BBidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
