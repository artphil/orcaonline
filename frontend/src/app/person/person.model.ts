export class UserModel {
  id: number;
  nomeFantasia: string;
}

export class PermissionModel {
  id: number;
  nome: string;
  descricao: string;
  
  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
  }
}

export class TipoUsuarioModel {
  id: number;
  nome: string;
  descricao: string;
  
  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
  }
}