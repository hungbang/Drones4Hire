import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BBidInfoComponent } from './b-bid-info.component';

describe('BBidInfoComponent', () => {
  let component: BBidInfoComponent;
  let fixture: ComponentFixture<BBidInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BBidInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BBidInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
