import { Component, OnInit } from '@angular/core';

import { UserTypeModel } from '../person.model';

@Component({
  selector: 'app-tipo-usuario',
  templateUrl: './tipo-usuario.component.html',
  styleUrls: ['./tipo-usuario.component.css']
})
export class TipoUsuarioComponent implements OnInit {

  tipo = new UserTypeModel();
  tipos = [];


  constructor() { }

  ngOnInit(): void {
  }

  adicionar() {
    console.log(this.tipo.nome);
    // this.permissionService.create(this.permission)
    //     .then(permission => {
    //       this.consultar();
    //       this.permission = new PermissionModel();
    //     });
  }

  excluir(id: number) {
    // console.log(id);
    // this.permissionService.delete(id)
    // .then(() => {
    //   this.consultar();
    // });
  }

  consultar(){
    // this.permissionService.getList()
    // .then(permissions => {
    //   this.permissions = permissions;
    // });
  }
  
  editar(t: UserTypeModel) {
    this.tipo = t;
  }

}
