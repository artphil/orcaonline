import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { PriceCollectionMapModel, UnidadeMedidaModel } from '../budget.model';
import { PriceCollectionMapService } from './price-collection-map.service';
import { ProductModel } from 'src/app/product/product.model';
import { ProductService } from 'src/app/product/product/product.service';
import { PriceCollectionMapItemModel } from '../budget.model';

import { PriceMapItemsComponent } from '../price-map-items/price-map-items.component';

import { BrickService } from 'src/app/product/brick/brick.service';
import { BrickModel } from 'src/app/product/product.model';


@Component({
  selector: 'app-price-collection-map',
  templateUrl: './price-collection-map.component.html',
  styleUrls: ['./price-collection-map.component.css']
})
export class PriceCollectionMapComponent implements OnInit {

  priceCollectionMap = new PriceCollectionMapModel();

  idPriceCollectionMap: number;

  itemAux = new PriceCollectionMapItemModel();

  brickList = [];
  productList = [];
  unidades = [];

  budgetsList = [];


  constructor(private route: ActivatedRoute,
    private messageService: MessageService,
    private priceCollectionMapServices: PriceCollectionMapService,
    private brickServices: BrickService,
    private productServices: ProductService
    ) { }

  ngOnInit(): void {
    this.idPriceCollectionMap = Number(this.route.snapshot.paramMap.get('cod'));
    this.getBrickList();
    this.getProductList();
    this.getUnidades();

    this.consult();
  }

  getBrickList(): void {
    this.brickServices.getList()
      .then((brickList: BrickModel[]) => {
        this.brickList = [];
        brickList.forEach(item => {
          this.brickList.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.brickList = [
          { label: 'Nenhum Brick cadastrado', value: null }
        ];
      });
  }

  getProductList(): void {
    this.productServices.getList()
      .then((productList: ProductModel[]) => {
        this.productList = [];
        productList.forEach(item => {
          this.productList.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.productList = [
          { label: 'Nenhum Produto cadastrado', value: null }
        ];
      });
  }

  getUnidades(): void {
    this.priceCollectionMapServices.getUnidades()
      .then((unidades: UnidadeMedidaModel[]) => {
        this.unidades = [];
        unidades.forEach(item => {
          this.unidades.push({ label: item, value: item });
        });
      })
      .catch(() => {
        this.productList = [
          { label: 'Nenhuma Unidade cadastrada', value: null }
        ];
      });
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
        this.priceCollectionMap = priceCollectionMap;
        this.messageService.add({ severity: 'sucess', summary: 'Cadastro realizado com sucesso.', detail: this.priceCollectionMap.id.toString()});
        this.idPriceCollectionMap = priceCollectionMap.id;
        console.log(priceCollectionMap);

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

  // adicionarItem(): void {
  //   this.itemAux.mapa = this.priceCollectionMap;
  //   console.log(this.itemAux)
  // }

  addItem(): void {
    this.save();
    this.itemAux.mapa.id = this.priceCollectionMap.id;
      this.priceCollectionMapServices.addItem(this.itemAux)
      .then ((priceCollectionMap: PriceCollectionMapModel) => {
        this.priceCollectionMap = priceCollectionMap;
        this.messageService.add({ severity: 'sucess', summary: 'Cadastro realizado com sucesso.', detail: this.priceCollectionMap.id.toString()});
        this.idPriceCollectionMap = priceCollectionMap.id;
        console.log(priceCollectionMap);

      })
      .catch((err) => {
        const msg = err.error [0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao adicionar Mapa de Coleta', detail: msg});
      });
  }



}

