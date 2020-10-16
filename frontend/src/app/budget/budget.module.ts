import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { CalendarModule } from 'primeng/calendar';
import { TableModule } from 'primeng/table';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { DropdownModule } from 'primeng/dropdown';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { TabViewModule } from 'primeng/tabview';

import { BudgetComponent, BudgetDialogComponent } from './budget/budget.component';
import { PriceCollectionMapComponent } from './price-collection-map/price-collection-map.component';
import { BudgetService } from './budget/budget.service';
import { BudgetListComponent } from './budget-list/budget-list.component';
import { PriceMapListComponent } from './price-map-list/price-map-list.component';
import { PriceMapItemsComponent } from './price-map-items/price-map-items.component';
import { PriceCollectionMapService } from './price-collection-map/price-collection-map.service';



@NgModule({
  declarations: [
    PriceCollectionMapComponent,
    BudgetComponent,
    BudgetListComponent,
    PriceMapListComponent,

    PriceMapItemsComponent,
    BudgetDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    CalendarModule,
    TableModule,
    InputTextModule,
    InputNumberModule,
    DynamicDialogModule,
    DropdownModule,
    TabViewModule
  ],
  exports: [
    PriceCollectionMapComponent,
    BudgetListComponent
  ],
  entryComponents: [
    PriceMapItemsComponent,
    BudgetDialogComponent
  ],
  providers: [
    BudgetService,
    PriceCollectionMapService
  ]
})
export class BudgetModule { }
