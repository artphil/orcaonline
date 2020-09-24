export class UserModel {
  id: number;
  nome: string;
  email: string;
  senha: string;
  cnpj: string;
  razaoSocial: string;
  nomeFantasia: string;
  tipoUsuario: UserTypeModel;

  constructor() {
     this.id = null;
     this.email = null;
     this.senha = null;
     this.cnpj = null;
     this.razaoSocial = null;
     this.nomeFantasia = null;
     this.tipoUsuario = new UserTypeModel();
  }
}

export class UserTypeModel {
  id: number;
  nome: string;
  descricao: string;
  permissoes: [];

  constructor() {
    this.id = null;
    this.nome = null;
    this.descricao = null;
    this.permissoes = [];
  }
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



