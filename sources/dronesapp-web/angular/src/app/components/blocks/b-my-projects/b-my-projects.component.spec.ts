import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BMyProjectsComponent } from './b-my-projects.component';

describe('BMyProjectsComponent', () => {
  let component: BMyProjectsComponent;
  let fixture: ComponentFixture<BMyProjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BMyProjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BMyProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
