import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LSocialComponent } from './l-social.component';

describe('LSocialComponent', () => {
  let component: LSocialComponent;
  let fixture: ComponentFixture<LSocialComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LSocialComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LSocialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
