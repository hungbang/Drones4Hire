import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BPaginationComponent } from './b-pagination.component';

describe('BPaginationComponent', () => {
  let component: BPaginationComponent;
  let fixture: ComponentFixture<BPaginationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BPaginationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BPaginationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
