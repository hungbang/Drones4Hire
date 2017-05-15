import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SServicesComponent } from './s-services.component';

describe('SServicesComponent', () => {
  let component: SServicesComponent;
  let fixture: ComponentFixture<SServicesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SServicesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
