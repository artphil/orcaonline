import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceMapListComponent } from './price-map-list.component';

describe('PriceMapListComponent', () => {
  let component: PriceMapListComponent;
  let fixture: ComponentFixture<PriceMapListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PriceMapListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceMapListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
