import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LPaginationComponent } from './l-pagination.component';

describe('LPaginationComponent', () => {
  let component: LPaginationComponent;
  let fixture: ComponentFixture<LPaginationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LPaginationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LPaginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
