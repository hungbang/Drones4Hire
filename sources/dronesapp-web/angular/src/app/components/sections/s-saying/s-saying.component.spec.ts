import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SSayingComponent } from './s-saying.component';

describe('SSayingComponent', () => {
  let component: SSayingComponent;
  let fixture: ComponentFixture<SSayingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SSayingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SSayingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
