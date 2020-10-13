import { UserModel } from '../person/person.model';
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
  marca: string;
  quantidade: number;
  unidade: UnidadeMedidaModel;
}

export class UnidadeMedidaModel {
  descricao: string;
  simbolo: string;
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
