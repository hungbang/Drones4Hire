import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BPortfolioComponent } from './b-portfolio.component';

describe('BPortfolioComponent', () => {
  let component: BPortfolioComponent;
  let fixture: ComponentFixture<BPortfolioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BPortfolioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
