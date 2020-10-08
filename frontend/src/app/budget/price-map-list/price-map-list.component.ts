import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';

import { PriceCollectionMapModel, PriceMapFilterModel } from '../budget.model';

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

  constructor() { }

  ngOnInit(): void {
  }

  consult(): void {}

  showItems(): void {}

  createBudget(): void {}

}
