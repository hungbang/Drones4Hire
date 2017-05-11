import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BReviewComponent } from './b-review.component';

describe('BReviewComponent', () => {
  let component: BReviewComponent;
  let fixture: ComponentFixture<BReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
