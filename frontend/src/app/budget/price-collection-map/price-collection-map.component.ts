import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { PriceCollectionMapModel } from '../budget.model';
import { PriceCollectionMapService } from './price-collection-map.service';
import { ProductModel } from 'src/app/product/product.model';
import { ProductService } from 'src/app/product/product/product.service';


@Component({
  selector: 'app-price-collection-map',
  templateUrl: './price-collection-map.component.html',
  styleUrls: ['./price-collection-map.component.css']
})
export class PriceCollectionMapComponent implements OnInit {

  priceCollectionMap = new PriceCollectionMapModel();

  idPriceCollectionMap: number;

  constructor(private route: ActivatedRoute,
    private messageService: MessageService,
    private priceCollectionMapServices: PriceCollectionMapService
    ) { }

  ngOnInit(): void {
    this.idPriceCollectionMap = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();
  }

  consult(): void {
    if (!this.idPriceCollectionMap) {
      this.priceCollectionMap = new PriceCollectionMapModel();
    } else {
      this.priceCollectionMapServices.getOne(this.idPriceCollectionMap)
      .then((priceCollectionMap: PriceCollectionMapModel) => {
        this.priceCollectionMap = priceCollectionMap ? priceCollectionMap : new PriceCollectionMapModel();
      })
      .catch(() => {
        this.priceCollectionMap = new PriceCollectionMapModel();
      })
    }
  }

  save(): void {

    if (!this.idPriceCollectionMap) {
      this.priceCollectionMapServices.create(this.priceCollectionMap)
      .then ((priceCollectionMap: PriceCollectionMapModel) => {
        this.messageService.add({ severity: 'sucess', summary: 'Cadastro realizado com sucesso.', detail: this.priceCollectionMap.id.toString()});
        this.idPriceCollectionMap = priceCollectionMap.id;
        this.consult();
      })
      .catch((err) => {
        const msg = err.error [0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao adicionar Mapa de Coleta', detail: msg});
      });
    }
    else {
      this.priceCollectionMapServices.update(this.priceCollectionMap)
      .then((priceCollectionMap: PriceCollectionMapModel) => {
        this.messageService.add({severity: 'sucess', summary: 'Alteração realizada com sucesso.', detail: priceCollectionMap.id.toString()});
        this.consult();
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao alterar Mapa de Coleta.'})
      });
    }
  }
}

