import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FClientProfileComponent } from './f-profile.component';

describe('FClientProfileComponent', () => {
  let component: FClientProfileComponent;
  let fixture: ComponentFixture<FClientProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FClientProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FClientProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
