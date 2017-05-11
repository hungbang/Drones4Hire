import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BProjectAddComponent } from './b-project-add.component';

describe('BProjectAddComponent', () => {
  let component: BProjectAddComponent;
  let fixture: ComponentFixture<BProjectAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BProjectAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BProjectAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
