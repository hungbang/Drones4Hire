import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BProjectDetailsComponent } from './b-project-details.component';

describe('BProjectDetailsComponent', () => {
  let component: BProjectDetailsComponent;
  let fixture: ComponentFixture<BProjectDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BProjectDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BProjectDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
