import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SAccountComponent } from './s-account.component';

describe('SAccountComponent', () => {
  let component: SAccountComponent;
  let fixture: ComponentFixture<SAccountComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SAccountComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
