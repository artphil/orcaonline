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

  constructor(){
    this.gtin = new GtinModel();
    this.brick = new BrickModel();
    this.classe = new ClassModel();
    this.familia = new FamilyModel();
    this.segmento = new SegmentModel();
  }
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
    this.gtin = new GtinModel();
  }
}

export class GtinModel {
  id: number;
  numero: number;
  brick: BrickModel;

  constructor() {
    this.id = null;
    this.numero = null;
    this.brick = new BrickModel();
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
    this.classe = new ClassModel();
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
    this.familia = new FamilyModel();
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
    this.segmento  = new SegmentModel();
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
