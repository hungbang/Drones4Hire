import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FProjectsSearchComponent } from './f-projects-search.component';

describe('FProjectsSearchComponent', () => {
  let component: FProjectsSearchComponent;
  let fixture: ComponentFixture<FProjectsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FProjectsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FProjectsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
