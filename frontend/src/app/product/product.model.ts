export class ProductModel {
  id: number;
  nome: string;
  descricao: string;
  ncm: string;
  gtin: string;

  constructor(value='') {
    this.id = null;
    this.nome = value;
    this.descricao = value;
    this.ncm = value;
    this.gtin = value;
  }
}
