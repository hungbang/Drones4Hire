import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FSearchComponent } from './f-search.component';

describe('FSearchComponent', () => {
  let component: FSearchComponent;
  let fixture: ComponentFixture<FSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
