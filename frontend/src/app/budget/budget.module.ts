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
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { TooltipModule } from 'primeng/tooltip';
import { DialogModule } from 'primeng/dialog';


import { BudgetComponent, BudgetDialogComponent } from './budget/budget.component';
import { PriceCollectionMapComponent } from './price-collection-map/price-collection-map.component';
import { BudgetService } from './budget/budget.service';
import { BudgetListComponent } from './budget-list/budget-list.component';
import { PriceMapListComponent } from './price-map-list/price-map-list.component';
import { PriceMapItemsComponent } from './price-map-items/price-map-items.component';
import { PriceCollectionMapService } from './price-collection-map/price-collection-map.service';
import { BudgetItemsComponent } from './budget-items/budget-items.component';



@NgModule({
  declarations: [
    PriceCollectionMapComponent,
    BudgetComponent,
    BudgetListComponent,
    PriceMapListComponent,

    PriceMapItemsComponent,
    BudgetDialogComponent,
    BudgetItemsComponent
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
    TabViewModule,
    OverlayPanelModule,
    TooltipModule,
    DialogModule
  ],
  exports: [
    PriceCollectionMapComponent,
    PriceMapListComponent,
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
