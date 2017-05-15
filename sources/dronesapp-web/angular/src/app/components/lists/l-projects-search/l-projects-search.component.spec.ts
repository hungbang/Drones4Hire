import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LProjectsSearchComponent } from './l-projects-search.component';

describe('LProjectSearchComponent', () => {
  let component: LProjectsSearchComponent;
  let fixture: ComponentFixture<LProjectsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LProjectsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LProjectsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
