import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapaColetaComponent } from './mapa-coleta/mapa-coleta.component';
import { BudgetComponent } from './budget/budget.component';



@NgModule({
  declarations: [
    MapaColetaComponent,
    BudgetComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    MapaColetaComponent
  ]
})
export class OrcamentoModule { }
