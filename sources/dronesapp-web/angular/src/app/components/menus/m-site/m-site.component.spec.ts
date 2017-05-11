import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MSiteComponent } from './m-site.component';

describe('MSiteComponent', () => {
  let component: MSiteComponent;
  let fixture: ComponentFixture<MSiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MSiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MSiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
