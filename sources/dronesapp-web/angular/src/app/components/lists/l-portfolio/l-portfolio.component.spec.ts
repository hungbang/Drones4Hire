import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LPortfolioComponent } from './l-portfolio.component';

describe('LPortfolioComponent', () => {
  let component: LPortfolioComponent;
  let fixture: ComponentFixture<LPortfolioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LPortfolioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
