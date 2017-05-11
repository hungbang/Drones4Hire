import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LCommentsComponent } from './l-comments.component';

describe('LCommentsComponent', () => {
  let component: LCommentsComponent;
  let fixture: ComponentFixture<LCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
