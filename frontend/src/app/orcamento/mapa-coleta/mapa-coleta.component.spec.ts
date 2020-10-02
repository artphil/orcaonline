import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MapaColetaComponent } from './mapa-coleta.component';

describe('MapaColetaComponent', () => {
  let component: MapaColetaComponent;
  let fixture: ComponentFixture<MapaColetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MapaColetaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MapaColetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
