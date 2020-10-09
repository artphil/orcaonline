import { Component, OnInit } from '@angular/core';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { PriceCollectionMapItemModel } from '../budget.model';

@Component({
  selector: 'app-price-map-items',
  templateUrl: './price-map-items.component.html',
  styleUrls: ['./price-map-items.component.css']
})
export class PriceMapItemsComponent implements OnInit {

  priceItems: PriceCollectionMapItemModel[];

  idPriceMap: number;

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) { }

  ngOnInit(): void {
    this.idPriceMap = this.config.data.id;

    this.consult(this.idPriceMap);

  }

  consult(id: number): void {
    // this.priceItems = [
    //   {
    //     id: 0,
    //     brick: {id:1, numero: 11},
    //     produto: null,
    //     marca: 'ardidas',
    //     quantidade: 1,
    //     unidade: {simbolo: 'm²'}
    //   }
    // ];
  }

}
