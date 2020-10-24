import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { PriceCollectionMapModel, UnidadeMedidaModel, PriceMapFilterModel, StatusModel } from '../budget.model';
import { PriceCollectionMapService } from './price-collection-map.service';
import { ProductModel } from 'src/app/product/product.model';
import { ProductService } from 'src/app/product/product/product.service';
import { PriceCollectionMapItemModel } from '../budget.model';
import { BrickService } from 'src/app/product/brick/brick.service';
import { BrickModel } from 'src/app/product/product.model';


import { DialogService } from 'primeng/dynamicdialog';

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

  showSearchDialog = false;
  filter = new PriceMapFilterModel();
  statusList: SelectItem[];
  priceMapList = [];

  constructor(private route: ActivatedRoute,
    private messageService: MessageService,
    private priceCollectionMapServices: PriceCollectionMapService,
    private brickServices: BrickService,
    private productServices: ProductService,
    private priceMapService: PriceCollectionMapService
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
      if (this.priceCollectionMap.descricao) {
        this.priceCollectionMapServices.create(this.priceCollectionMap)
          .then((priceCollectionMap: PriceCollectionMapModel) => {
            this.priceCollectionMap = priceCollectionMap;
            this.messageService.add(
              { severity: 'sucess', summary: 'Cadastro realizado com sucesso.', detail: this.priceCollectionMap.id.toString() }
            );
            this.idPriceCollectionMap = priceCollectionMap.id;
            console.log(priceCollectionMap);

          })
          .catch((err) => {
            const msg = err.error[0].mensagemUsuario;
            this.messageService.add({ severity: 'error', summary: 'Falha ao adicionar Mapa de Coleta', detail: msg });
          });
      }
      else {
        this.messageService.add({ severity: 'error', summary: 'Informe a descrição.', detail: "" });
      }
    }
    else {
      this.priceCollectionMapServices.update(this.priceCollectionMap)
        .then((priceCollectionMap: PriceCollectionMapModel) => {
          this.messageService.add(
            { severity: 'sucess', summary: 'Alteração realizada com sucesso.', detail: priceCollectionMap.id.toString() }
          );
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao alterar Mapa de Coleta.' })
        });
    }
  }

  addItem(): void {
    this.save();
    this.itemAux.mapa.id = this.priceCollectionMap.id;
    this.priceCollectionMapServices.addItem(this.itemAux)
      .then((priceCollectionMap: PriceCollectionMapModel) => {
        this.priceCollectionMap = priceCollectionMap;
        this.messageService.add(
          { severity: 'sucess', summary: 'Cadastro realizado com sucesso.', detail: this.priceCollectionMap.id.toString() }
        );
        this.idPriceCollectionMap = priceCollectionMap.id;
        console.log(priceCollectionMap);

      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao adicionar Mapa de Coleta', detail: msg });
      });
  }

  search(): void {

    this.statusList = StatusModel.getPriceCollectionMapStatus();
    this.showSearchDialog = true;
  }

  consultList(): void {
    this.priceMapService.getByFilter(this.filter)
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

  selectMap(idMap): void {
    this.priceMapService.getOne(idMap)
      .then((map: PriceCollectionMapModel) => {
        this.priceCollectionMap = map;
        this.showSearchDialog = false;
        this.idPriceCollectionMap = idMap;
      })
      .catch(() => {
        this.messageService.add(
          { severity: 'error', summary: 'Erro', detail: 'Não foi possivel carregar o mapa.' }
        );
      });
  }

  closeMap(): void {
    this.priceMapService.closeMap(this.idPriceCollectionMap)
      .then((map: PriceCollectionMapModel) => {
        this.priceCollectionMap = map;
      })
      .catch(() => {
        this.messageService.add(
          { severity: 'error', summary: 'Erro', detail: 'Não foi possivel encerrar o mapa.' }
        );
      });

  }

  deleteMap(): void {
    this.priceMapService.delete(this.idPriceCollectionMap)
      .then(() => {
        this.ngOnInit();
      })
      .catch(() => {
        this.messageService.add(
          { severity: 'error', summary: 'Erro', detail: 'Não foi possivel deletar o mapa.' }
        );
      });
  }

  startPriceCollection(): void {
    this.priceMapService.startPriceCollection(this.idPriceCollectionMap)
      .then((map: PriceCollectionMapModel) => {
        this.priceCollectionMap = map;
      })
      .catch(() => {
        this.messageService.add(
          { severity: 'error', summary: 'Erro', detail: 'Não foi possivel iniciar a cotação.' }
        );
      });
  }

  clear(): void {
    this.idPriceCollectionMap = null;
    this.priceCollectionMap = new PriceCollectionMapModel;
  }

}

