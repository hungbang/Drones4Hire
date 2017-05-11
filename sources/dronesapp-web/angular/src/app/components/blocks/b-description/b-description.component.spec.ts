import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BDescriptionComponent } from './b-description.component';

describe('BDescriptionComponent', () => {
  let component: BDescriptionComponent;
  let fixture: ComponentFixture<BDescriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BDescriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
