import { UserModel } from '../person/person.model';
import { ProductModel } from '../product/product.model';

export class BudgetModel {
  id: number;
  dataRegistro: Date;
  aprovado: boolean;
  fornecedor: UserModel;
  status: StatusModel;
  mapa: MapaColetaModel;
  itens: BudgetItemModel[];

  constructor() {
    this.id = null;
    this.dataRegistro = new Date();
    this.aprovado = null;
    this.fornecedor = null;
    this.status = new StatusModel();
    this.mapa = new MapaColetaModel();
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
  itemMapa: MapaColetaItemModel;
  produto: ProductModel;
}

export class MapaColetaModel { }

export class MapaColetaItemModel { }

export class UnidadeMedidaModel {
  descricao: string;
  simbolo: string;
}
