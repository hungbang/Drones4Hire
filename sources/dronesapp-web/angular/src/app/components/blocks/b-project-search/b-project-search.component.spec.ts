import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BProjectSearchComponent } from './b-project-search.component';

describe('BProjectSearchComponent', () => {
  let component: BProjectSearchComponent;
  let fixture: ComponentFixture<BProjectSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BProjectSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BProjectSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
