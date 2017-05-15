import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SGoingComponent } from './s-going.component.ts';

describe('SGoingComponent', () => {
  let component: SGoingComponent;
  let fixture: ComponentFixture<SGoingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SGoingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SGoingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
