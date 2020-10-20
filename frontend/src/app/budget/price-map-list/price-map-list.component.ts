import { Component, Input, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

import { BudgetItemModel, BudgetModel, PriceCollectionMapItemModel, PriceCollectionMapModel, PriceMapFilterModel } from '../budget.model';
import { BudgetComponent, BudgetDialogComponent } from '../budget/budget.component';
import { BudgetService } from '../budget/budget.service';
import { PriceCollectionMapService } from '../price-collection-map/price-collection-map.service';
import { PriceMapItemsComponent } from '../price-map-items/price-map-items.component';

@Component({
  selector: 'app-price-map-list',
  templateUrl: './price-map-list.component.html',
  styleUrls: ['./price-map-list.component.css']
})
export class PriceMapListComponent implements OnInit {

  filter = new PriceMapFilterModel();
  priceMapList: PriceCollectionMapModel[];

  statusList: SelectItem[];
  productList: SelectItem[];

  @Input() showAll = true;

  constructor(
    private dialogService: DialogService,
    private messageService: MessageService,
    private priceMapService: PriceCollectionMapService,
    private budgetService: BudgetService
  ) { }

  ngOnInit(): void {
    this.consult();
  }

  consult(): void {
    this.priceMapService.getList()
      .then((priceMaps: PriceCollectionMapModel[]) => {
        this.priceMapList = priceMaps ? priceMaps : [];
      })
      .catch(() => {
        this.priceMapList = [];
      });
  }

  showItems(id: number, items: PriceCollectionMapItemModel[]): void {
    const ref = this.dialogService.open(PriceMapItemsComponent, {
      width: '50%',
      data: { id, items }
    });
  }

  createBudget(idMap: number): void {
    this.budgetService.create(idMap)
      .then((budget: BudgetModel) => {

        const ref = this.dialogService.open(BudgetDialogComponent, {
          width: '80%',
          data: { budget }
        });
      })
      .catch(() => {
        this.messageService.add(
            { severity: 'error', summary: 'Erro', detail: 'Não foi possivel gerar orçamento.' }
        );
      });

  }

}
