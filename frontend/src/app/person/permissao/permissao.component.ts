import { Component, OnInit } from '@angular/core';

import { SelectItem } from 'primeng/api';

import { PermissionModel, Modulo } from '../person.model';
import { PermissionService } from './permission.service';

@Component({
  selector: 'app-permissao',
  templateUrl: './permissao.component.html',
  styleUrls: ['./permissao.component.css']
})
export class PermissaoComponent implements OnInit {

  permission = new PermissionModel();
  permissions = [];
  modulos: SelectItem[];

  constructor(private permissionService: PermissionService) { }

  ngOnInit(): void {
    this.consultar();
    this.iniciaModulos();
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

  iniciaModulos(): void {
    this.permissionService.getModulos()
      .then(itens => {
        this.modulos = [];
        for(var a of itens){
          this.modulos.push({ label: a, value: a });
        }
      })
      .catch(() => {
        this.modulos = [
          { label: 'Nenhum módulo cadastrado', value: null }
        ];
      });
  }



  editar(p: PermissionModel) {
    this.permission = p;
  }

}



