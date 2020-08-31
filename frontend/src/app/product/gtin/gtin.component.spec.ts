import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GtinComponent } from './gtin.component';

describe('GtinComponent', () => {
  let component: GtinComponent;
  let fixture: ComponentFixture<GtinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GtinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GtinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
