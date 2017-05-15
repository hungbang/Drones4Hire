import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BCommentsComponent } from './b-comments.component';

describe('BCommentsComponent', () => {
  let component: BCommentsComponent;
  let fixture: ComponentFixture<BCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
