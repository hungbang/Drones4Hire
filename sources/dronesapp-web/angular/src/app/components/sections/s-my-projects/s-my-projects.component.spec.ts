import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SMyProjectsComponent } from './s-my-projects.component';

describe('SMyProjectsComponent', () => {
  let component: SMyProjectsComponent;
  let fixture: ComponentFixture<SMyProjectsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SMyProjectsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SMyProjectsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
