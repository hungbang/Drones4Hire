import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FCommentsComponent } from './f-comments.component';

describe('FCommentsComponent', () => {
  let component: FCommentsComponent;
  let fixture: ComponentFixture<FCommentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FCommentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
