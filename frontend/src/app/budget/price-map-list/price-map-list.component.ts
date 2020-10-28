import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { AuthService } from 'src/app/security/auth.service';

import { BudgetItemModel, BudgetModel, PriceCollectionMapItemModel, PriceCollectionMapModel, PriceMapFilterModel, StatusModel } from '../budget.model';
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

  isBuyer: boolean;
  isSeller: boolean;

  @Input() showAll = true;

  constructor(
    private router: Router,
    private auth: AuthService,
    private dialogService: DialogService,
    private messageService: MessageService,
    private priceMapService: PriceCollectionMapService,
    private budgetService: BudgetService
  ) { }

  ngOnInit(): void {
    this.consult();

    this.statusList = StatusModel.selectItems();

    this.isBuyer = this.auth.hasPermission('ROLE_CADASTRAR_MAPACOLETA');
    this.isSeller = this.auth.hasPermission('ROLE_CADASTRAR_ORCAMENTO');
  }

  consult(): void {
    this.priceMapService.getByFilterRunning(this.filter)
      .then((priceMaps: PriceCollectionMapModel[]) => {
        if (priceMaps) {
          priceMaps.forEach((priceMap) => {
            priceMap.dataRegistro = new Date(priceMap.dataRegistro);
          });
          this.priceMapList = priceMaps;
        } else {
          this.priceMapList = [];
        }
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

        this.router.navigate([`/bdt/${budget.id}`])

        // const ref = this.dialogService.open(BudgetDialogComponent, {
        //   width: '80%',
        //   data: { budget }
        // });
      })
      .catch(() => {
        this.messageService.add(
            { severity: 'error', summary: 'Erro', detail: 'Não foi possivel gerar orçamento.' }
        );
      });

  }

  editMap(mapId: number): void {
    this.router.navigate([`mapc/${mapId}`]);
  }

}
