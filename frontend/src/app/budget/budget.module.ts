import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import {CalendarModule} from 'primeng/calendar';

import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';

import { BudgetComponent } from './budget/budget.component';
import { PriceCollectionMapComponent } from './price-collection-map/price-collection-map.component';
import { HttpClientModule } from '@angular/common/http';
import { BudgetService } from './budget/budget.service';


@NgModule({
  declarations: [
    PriceCollectionMapComponent,
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
  PriceCollectionMapComponent,
  ],
  providers: [
    BudgetService
  ]
})
export class BudgetModule { }
