import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LAbilitiesComponent } from './l-abilities.component';

describe('LAbilitiesComponent', () => {
  let component: LAbilitiesComponent;
  let fixture: ComponentFixture<LAbilitiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LAbilitiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LAbilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
