import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SSearchComponent } from './s-search.component';

describe('SSearchComponent', () => {
  let component: SSearchComponent;
  let fixture: ComponentFixture<SSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
