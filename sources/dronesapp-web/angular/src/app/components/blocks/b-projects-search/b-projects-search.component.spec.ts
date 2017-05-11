import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BProjectsSearchComponent } from './b-projects-search.component';

describe('BProjectsSearchComponent', () => {
  let component: BProjectsSearchComponent;
  let fixture: ComponentFixture<BProjectsSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BProjectsSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BProjectsSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
