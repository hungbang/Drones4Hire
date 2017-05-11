import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BProjectDescriptionComponent } from './b-project-description.component';

describe('BProjectDescriptionComponent', () => {
  let component: BProjectDescriptionComponent;
  let fixture: ComponentFixture<BProjectDescriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BProjectDescriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BProjectDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
