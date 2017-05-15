import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LSimilarComponent } from './l-similar.component';

describe('LSimilarComponent', () => {
  let component: LSimilarComponent;
  let fixture: ComponentFixture<LSimilarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LSimilarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LSimilarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
