export class ProductModel {
  id: string;
  nome: string;
  descricao: string;
  segmento: string;
  familia: string;
  classe: string;

  constructor(value='') {
    this.id = value;
    this.nome = value;
    this.descricao = value;
    this.segmento = value;
    this.familia = value;
    this.classe = value;
  }
}
