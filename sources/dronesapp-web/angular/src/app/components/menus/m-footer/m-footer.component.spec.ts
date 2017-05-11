import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MFooterComponent } from './m-footer.component.ts';

describe('MFooterComponent', () => {
  let component: MFooterComponent;
  let fixture: ComponentFixture<MFooterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MFooterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
