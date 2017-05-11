import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SProjectsSearchComponent } from './s-projects-search.component';

describe('SProjectsSearchComponent', () => {
  let component: SProjectsSearchComponent;
  let fixture: ComponentFixture<SProjectsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SProjectsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SProjectsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
