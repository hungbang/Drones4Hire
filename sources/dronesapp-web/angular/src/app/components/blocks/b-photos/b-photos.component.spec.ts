import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BPhotosComponent } from './b-photos.component';

describe('BPhotosComponent', () => {
  let component: BPhotosComponent;
  let fixture: ComponentFixture<BPhotosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BPhotosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BPhotosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
