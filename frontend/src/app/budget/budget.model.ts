import { UserModel } from '../person/person.model';
import { BrickComponent } from '../product/brick/brick.component';
import { BrickService } from '../product/brick/brick.service';
import { BrickModel, ProductModel } from '../product/product.model';

export class BudgetModel {
  id: number;
  dataRegistro: Date;
  aprovado: boolean;
  fornecedor: UserModel;
  status: StatusModel;
  mapa: PriceCollectionMapModel;
  itens: BudgetItemModel[];

  constructor() {
    this.id = null;
    this.dataRegistro = new Date();
    this.aprovado = null;
    this.fornecedor = null;
    this.status = new StatusModel();
    this.mapa = new PriceCollectionMapModel();
    this.itens = [];
  }
}

export class StatusModel {
  id: number;
  nome: string;

  constructor() {
    this.id = null;
    this.nome = null;
  }
}

export class BudgetItemModel {
  id: number;
  valorUnitario: number;
  valorUnitarioPrazo: number;
  orcamento: any;
  itemMapa: PriceCollectionMapItemModel;
  produto: ProductModel;
}

export class PriceCollectionMapModel {
  id: number;
  dataCriacao: Date;
  comprador: UserModel;
  status: StatusModel;
  itens: PriceCollectionMapItemModel[];
  descricao: string;

  constructor() {
    this.id = null;
    this.dataCriacao = new Date();
    this.comprador = null;
    this.status = new StatusModel();
    this.itens = [];
    this.descricao = null;
  }
 }

export class PriceCollectionMapItemModel {
  id: number;
  brick: BrickModel;
  produto: ProductModel;
  quantidade: number;
  unidade: UnidadeMedidaModel;
  mapa: PriceCollectionMapModel;

  constructor() {
    this.id = null;
    this.brick = new BrickModel();
    this.produto = new ProductModel();
    this.quantidade = null;
    this.unidade = new UnidadeMedidaModel();
    this.mapa = null;
  }
}

export class UnidadeMedidaModel {
  descricao: string;
  simbolo: string;

  constructor(){
    this.descricao = null;
    this.simbolo = null
  }
}

export class PriceMapFilterModel {
  status: StatusModel;
  produto: ProductModel;
  comprador: UserModel;
  dataInicio: Date;
  dataFim: Date;

  constructor() {
    this.dataInicio = new Date();
    this.dataFim = new Date();
    this.comprador = new UserModel();
    this.status = new StatusModel();
    this.produto = new ProductModel();
  }
 }
