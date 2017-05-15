import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BSimilarComponent } from './b-similar.component';

describe('BSimilarComponent', () => {
  let component: BSimilarComponent;
  let fixture: ComponentFixture<BSimilarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BSimilarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BSimilarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
