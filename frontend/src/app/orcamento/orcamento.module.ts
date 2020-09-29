import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapaColetaComponent } from './mapa-coleta/mapa-coleta.component';



@NgModule({
  declarations: [MapaColetaComponent],
  imports: [
    CommonModule
  ],
  exports: [
    MapaColetaComponent
  ]
})
export class OrcamentoModule { }
