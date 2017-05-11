import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SPreviewComponent } from './s-preview.component';

describe('SPreviewComponent', () => {
  let component: SPreviewComponent;
  let fixture: ComponentFixture<SPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
