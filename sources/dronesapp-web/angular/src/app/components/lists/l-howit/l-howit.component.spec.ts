import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LHowitComponent } from './l-howit.component';

describe('LHowitComponent', () => {
  let component: LHowitComponent;
  let fixture: ComponentFixture<LHowitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LHowitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LHowitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
