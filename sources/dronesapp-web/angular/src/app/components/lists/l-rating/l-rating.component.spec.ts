import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LRatingComponent } from './l-rating.component';

describe('LRatingComponent', () => {
  let component: LRatingComponent;
  let fixture: ComponentFixture<LRatingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LRatingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LRatingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
