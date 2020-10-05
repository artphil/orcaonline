import { Component, OnInit } from '@angular/core';

import { MessageService, SelectItem } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';

import { PermissionModel, Modulo } from '../person.model';
import { PermissionService } from './permission.service';

@Component({
  selector: 'app-permission',
  templateUrl: './permission.component.html',
  styleUrls: ['./permission.component.css']
})
export class PermissaoComponent implements OnInit {

  permission = new PermissionModel();
  permissions = [];
  modulos: SelectItem[];

  constructor(
    private permissionService: PermissionService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.consultar();
    this.iniciaModulos();
  }

  adicionar() {
    this.permissionService.create(this.permission)
      .then(permission => {
        this.messageService.add(
          { severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: permission.nome }
        );
        this.consultar();
        this.permission = new PermissionModel();
      })
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  excluir(id: number) {
    this.permissionService.delete(id)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Permissão Excluida com Sucesso.', detail: `O id ${id} não pode mais ser acessado` }
        );
        this.consultar();
      })
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  consultar() {
    this.permissionService.getList()
      .then(permissions => {
        this.permissions = permissions;
      })
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  iniciaModulos(): void {
    this.permissionService.getModulos()
      .then(itens => {
        this.modulos = [];
        for (var a of itens) {
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



