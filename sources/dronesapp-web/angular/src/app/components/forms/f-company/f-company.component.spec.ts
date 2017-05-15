import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FClientCompanyComponent } from './f-company.component';

describe('FClientCompanyComponent', () => {
  let component: FClientCompanyComponent;
  let fixture: ComponentFixture<FClientCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FClientCompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FClientCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
