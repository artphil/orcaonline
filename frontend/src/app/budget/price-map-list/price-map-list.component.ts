import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

import { PriceCollectionMapModel, PriceMapFilterModel } from '../budget.model';
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

  constructor(
    private dialogService: DialogService,
    private priceMapService: PriceCollectionMapService
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

  showItems(id: number): void {
    const ref = this.dialogService.open(PriceMapItemsComponent, {
      width: '50%',
      data: { id }
    });
  }

  createBudget(): void { }

}
