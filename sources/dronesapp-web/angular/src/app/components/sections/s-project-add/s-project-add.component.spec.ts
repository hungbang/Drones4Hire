import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SProjectAddComponent } from './s-project-add.component';

describe('SProjectAddComponent', () => {
  let component: SProjectAddComponent;
  let fixture: ComponentFixture<SProjectAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SProjectAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SProjectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
