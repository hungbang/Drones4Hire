import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FAuthorizationComponent } from './f-authorization.component';

describe('FAuthorizationComponent', () => {
  let component: FAuthorizationComponent;
  let fixture: ComponentFixture<FAuthorizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FAuthorizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FAuthorizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
