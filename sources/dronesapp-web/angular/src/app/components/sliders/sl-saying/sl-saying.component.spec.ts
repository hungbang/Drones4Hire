import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SlSayingComponent } from './sl-saying.component';

describe('SlSayingComponent', () => {
  let component: SlSayingComponent;
  let fixture: ComponentFixture<SlSayingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SlSayingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SlSayingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
