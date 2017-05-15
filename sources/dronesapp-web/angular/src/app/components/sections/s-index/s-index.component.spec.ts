import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SIndexComponent } from './s-index.component';

describe('SIndexComponent', () => {
  let component: SIndexComponent;
  let fixture: ComponentFixture<SIndexComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SIndexComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SIndexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
