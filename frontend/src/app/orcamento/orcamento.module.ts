import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import {CalendarModule} from 'primeng/calendar';

import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';

import { BudgetComponent } from './budget/budget.component';
import { MapaColetaComponent } from './mapa-coleta/mapa-coleta.component';
import { HttpClientModule } from '@angular/common/http';
import { BudgetService } from './budget/budget.service';


@NgModule({
  declarations: [
    MapaColetaComponent,
    BudgetComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    CalendarModule,
    TableModule,
    InputTextModule
  ],
  exports: [
    MapaColetaComponent,
  ],
  providers: [
    BudgetService
  ]
})
export class OrcamentoModule { }
