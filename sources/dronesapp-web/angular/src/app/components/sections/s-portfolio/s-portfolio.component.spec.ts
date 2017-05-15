import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SPortfolioComponent } from './s-portfolio.component';

describe('SPortfolioComponent', () => {
  let component: SPortfolioComponent;
  let fixture: ComponentFixture<SPortfolioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SPortfolioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
