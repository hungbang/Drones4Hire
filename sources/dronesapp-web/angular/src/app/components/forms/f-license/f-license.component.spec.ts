import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FPilotLicenseComponent } from './f-license.component';

describe('FPilotLicenseComponent', () => {
  let component: FPilotLicenseComponent;
  let fixture: ComponentFixture<FPilotLicenseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FPilotLicenseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FPilotLicenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
