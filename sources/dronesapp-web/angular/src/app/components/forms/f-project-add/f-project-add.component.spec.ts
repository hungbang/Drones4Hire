import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FProjectAddComponent } from './f-project-add.component';

describe('FProjectAddComponent', () => {
  let component: FProjectAddComponent;
  let fixture: ComponentFixture<FProjectAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FProjectAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FProjectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
