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
  orcamentos: BudgetModel[];

  constructor() {
    this.id = null;
    this.dataRegistro = new Date();
    this.aprovado = null;
    this.fornecedor = null;
    this.status = new StatusModel();
    this.mapa = new PriceCollectionMapModel();
    this.itens = [];
    this.orcamentos = [];
  }
}

export class BudgetFilterModel {
  dataRegistroInicial: Date;
  dataRegistroFinal: Date;
  dataEnvioInicial: Date;
  dataEnvioFinal: Date;
  idStatus: number;

  constructor() {
    this.dataRegistroInicial = null;
    this.dataRegistroInicial = null;
    this.dataRegistroFinal = null;
    this.dataEnvioInicial = null;
    this.dataEnvioInicial = null;
    this.dataEnvioFinal = null;
    this.idStatus = null;
  }

}

export class StatusModel {
  id: number;
  nome: string;

  constructor() {
    this.id = null;
    this.nome = null;
  }

  static selectItems(): any {
    const status = [
      { value: null, label: 'Todos' },
      { value: 1, label: 'Aberto' },
      { value: 2, label: 'Ativo' },
      { value: 3, label: 'Fechado' },
      { value: 4, label: 'Cancelado' },
      { value: 5, label: 'Inativo' },
      { value: 6, label: 'Em andamento' }
    ];
    return status;
  }

  static getPriceCollectionMapStatus(): any {
    const status = [
      { value: null, label: 'Todos' },
      { value: 1, label: 'Aberto' },      
      { value: 3, label: 'Fechado' },
      { value: 6, label: 'Em andamento' }
    ];
    return status;
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
  dataRegistro: Date;
  comprador: UserModel;
  status: StatusModel;
  itens: PriceCollectionMapItemModel[];
  orcamentos: BudgetModel[];
  descricao: string;
  totalAVista: number;
  totalAPrazo: number;

  constructor() {
    this.id = null;
    this.dataRegistro = new Date();
    this.comprador = null;
    this.status = new StatusModel();
    this.itens = [];
    this.orcamentos = [];
    this.descricao = null;
    this.totalAVista = 0;
    this.totalAPrazo = 0;
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
    this.mapa = new PriceCollectionMapModel();
  }
}

export class UnidadeMedidaModel {
  descricao: string;
  simbolo: string;

  constructor() {
    this.descricao = null;
    this.simbolo = null;
  }
}

export class PriceMapFilterModel {
  dataRegistroInicial: Date;
  dataRegistroFinal: Date;
  dataEncerramentoInicial: Date;
  dataEncerramentoFinal: Date;
  idStatus: number;

  constructor() {
    this.dataRegistroInicial = null;
    this.dataRegistroInicial = null;
    this.dataRegistroFinal = null;
    this.dataEncerramentoInicial = null;
    this.dataEncerramentoInicial = null;
    this.dataEncerramentoFinal = null;
    this.idStatus = null;
  }

}
