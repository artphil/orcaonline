import { Component, OnInit } from '@angular/core';

import { PermissionModel } from '../person.model';
import { PermissionService } from './permission.service';

@Component({
  selector: 'app-permissao',
  templateUrl: './permissao.component.html',
  styleUrls: ['./permissao.component.css']
})
export class PermissaoComponent implements OnInit {

  permission = new PermissionModel();
  permissions = [];

  constructor(private permissionService: PermissionService) { }

  ngOnInit(): void {
    this.consultar();
  }

  adicionar() {
    this.permissionService.create(this.permission)
        .then(permission => {
          this.consultar();
          this.permission = new PermissionModel();
        });
  }

  excluir(id: number) {
    this.permissionService.delete(id)
    .then(() => {
      this.consultar();
    });
  }

  consultar(){
    this.permissionService.getList()
    .then(permissions => {
      this.permissions = permissions;
    });
  }
  
  editar(p: PermissionModel) {
    this.permission = p;
  }

}



