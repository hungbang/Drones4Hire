import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AProjectComponent } from './a-project.component.ts';

describe('AProjectComponent', () => {
  let component: AProjectComponent;
  let fixture: ComponentFixture<AProjectComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AProjectComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AProjectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
