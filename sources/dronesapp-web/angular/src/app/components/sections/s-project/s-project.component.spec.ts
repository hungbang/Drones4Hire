import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SProject } from './s-project.component';

describe('SProject', () => {
  let component: SProject;
  let fixture: ComponentFixture<SProject>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SProject ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SProject);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
