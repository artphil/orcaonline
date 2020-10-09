import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PriceCollectionMapComponent } from './price-collection-map.component';

describe('PriceCollectionMapComponent', () => {
  let component: PriceCollectionMapComponent;
  let fixture: ComponentFixture<PriceCollectionMapComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PriceCollectionMapComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PriceCollectionMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
