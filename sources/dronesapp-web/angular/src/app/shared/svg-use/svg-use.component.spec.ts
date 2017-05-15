/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SvgUseComponent } from './svg-use.component';

describe('SvgUseComponent', () => {
  let component: SvgUseComponent;
  let fixture: ComponentFixture<SvgUseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SvgUseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SvgUseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
