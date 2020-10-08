import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

import { PriceCollectionMapModel, PriceMapFilterModel } from '../budget.model';
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
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    const pcmi = {
      id: 0,
      brick: null,
      produto: null,
      marca: 'ardidas',
      quantidade: 1,
      unidade: null
    };

    const pcm = new PriceCollectionMapModel();
    pcm.comprador = {nome:'Tião'};
    pcm.itens.push(pcmi);

    this.priceMapList = [pcm];
  }

  consult(): void { }

  showItems(id: number): void {
    const ref = this.dialogService.open(PriceMapItemsComponent, {
      width: '50%',
      data: { id }
    });
  }

  createBudget(): void { }

}
