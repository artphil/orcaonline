import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoUsuarioComponent } from './tipo-usuario.component';

describe('TipoUsuarioComponent', () => {
  let component: TipoUsuarioComponent;
  let fixture: ComponentFixture<TipoUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TipoUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TipoUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
