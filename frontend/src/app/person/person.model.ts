export class UserModel {
  id: number;
  nome: string;
  email: string;
  senha: string;
  cnpj: string;
  razaoSocial: string;
  nomeFantasia: string;
  tipoUsuario: UserTypeModel;

  constructor(data = null) {
    if (data) {
      this.id = data.id;
      this.email = data.email;
      this.senha = data.senha;
      this.cnpj = data.cnpj;
      this.nome = data.nome;
      this.nomeFantasia = data.nomeFantasia;
      this.tipoUsuario = data.tipoUsuario;

    } else {
      this.id = null;
      this.email = null;
      this.senha = null;
      this.cnpj = null;
      this.nome = null;
      this.nomeFantasia = null;
      this.tipoUsuario = new UserTypeModel();

    }
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
  modulo: string;
  descricao: string;

  constructor() {
    this.id = null;
    this.nome = null;
    this.modulo = null;
    this.descricao = null;
  }
}

export class Modulo{
   numero: number;
   nome: string;

   constructor() {
    this.numero = null;
    this.nome = null;
  }
}


