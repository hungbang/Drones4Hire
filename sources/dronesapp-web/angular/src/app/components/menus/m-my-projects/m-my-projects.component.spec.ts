import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MMyProjectsComponent } from './m-my-projects.component';

describe('MMyProjectsComponent', () => {
  let component: MMyProjectsComponent;
  let fixture: ComponentFixture<MMyProjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MMyProjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MMyProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
