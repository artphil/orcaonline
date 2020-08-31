export class ProductFilterModel {
  id: number;
  nome: string;
  descricao: string;
  ncm: string;
  gtin: GtinModel;
  brick: BrickModel;
  classe: ClassModel;
  familia: FamilyModel;
  segmento: SegmentModel;
}

export class ProductModel {
  id: number;
  nome: string;
  descricao: string;
  ncm: string;
  gtin: GtinModel;

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
    this.ncm = null;
    this.gtin = null;
  }
}

export class GtinModel {
  id: number;
  numero: number;
  brick: BrickModel;

  constructor() {
    this.id = null;
    this.numero = null;
    this.brick = null;
  }
}

export class BrickModel {
  id: number;
  nome: string;
  descricao: string;
  classe: ClassModel;

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
    this.classe = null;
  }
}

export class ClassModel {
  id: number;
  nome: string;
  descricao: string;
  familia: FamilyModel;

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
    this.familia = null;
  }
}

export class FamilyModel {
  id: number;
  nome: string;
  descricao: string;
  segmento: SegmentModel;

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
    this.segmento = null;
  }
}

export class SegmentModel {
  id: number;
  nome: string;
  descricao: string;

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
  }
}