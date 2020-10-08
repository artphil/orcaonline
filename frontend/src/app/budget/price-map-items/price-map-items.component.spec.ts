import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceMapItemsComponent } from './price-map-items.component';

describe('PriceMapItemsComponent', () => {
  let component: PriceMapItemsComponent;
  let fixture: ComponentFixture<PriceMapItemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PriceMapItemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceMapItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
