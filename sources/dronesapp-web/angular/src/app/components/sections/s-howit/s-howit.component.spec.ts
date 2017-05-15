import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SHowitComponent } from './s-howit.component';

describe('SHowitComponent', () => {
  let component: SHowitComponent;
  let fixture: ComponentFixture<SHowitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SHowitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SHowitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
