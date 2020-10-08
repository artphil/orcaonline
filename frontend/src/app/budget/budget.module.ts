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
import { BudgetListComponent } from './budget-list/budget-list.component';


@NgModule({
  declarations: [
    PriceCollectionMapComponent,
    BudgetComponent,
    BudgetListComponent
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
  BudgetListComponent
  ],
  providers: [
    BudgetService
  ]
})
export class BudgetModule { }
