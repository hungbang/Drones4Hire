import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SAuthorizationComponent } from './s-authorization.component';

describe('SAuthorizationComponent', () => {
  let component: SAuthorizationComponent;
  let fixture: ComponentFixture<SAuthorizationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SAuthorizationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SAuthorizationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
