import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LServicesComponent } from './l-services.component';

describe('LServicesComponent', () => {
  let component: LServicesComponent;
  let fixture: ComponentFixture<LServicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LServicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
