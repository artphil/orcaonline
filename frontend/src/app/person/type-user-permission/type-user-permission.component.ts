import { Component, OnInit, TestabilityRegistry } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { SelectItem } from 'primeng/api';

import { UserTypeModel } from '../person.model';
import { UserTypeService } from '../user-type/user-type.service';
import { PermissionService } from '../permission/permission.service';



@Component({
  selector: 'app-type-user-permission',
  templateUrl: './type-user-permission.component.html',
  styles: [`
  .red {
    background-color: red;
  }
  .green {
    background-color: green;
  }`
],
  styleUrls: ['./type-user-permission.component.css']
})
export class TypeUserPermissionComponent implements OnInit {
  
  userType: SelectItem;
  userTypes: SelectItem[];
  idUserType = null;
  modulo = '';
  modulos: SelectItem[];
  permissions: PermissionModelAux [];

  constructor(private route: ActivatedRoute, private userTypeService: UserTypeService, private permissionService: PermissionService ) {}

  ngOnInit(): void {
    this.idUserType = Number(this.route.snapshot.paramMap.get('cod'));
    this.setUserTypes();
    this.setModulos();
    
  }

  setUserTypes(): void {
    this.userTypeService.getList()
      .then(itens => {
        this.userTypes = [];
        for(var u of itens){
          if(Number(u.id) != this.idUserType){ 
            this.userTypes.push({ label: u.nome, value: u.id });
          }
          else{
            this.userType = { label: u.nome, value: u.id };
          }
        }
        this.userTypes.unshift(this.userType);
        this.setPermissoes();
      })
      .catch(() => {
        this.userTypes = [
          { label: 'Nenhum tipo de usuário cadastrado', value: null }
        ];
      });
  }

  setModulos(): void {
    this.permissionService.getModulos()
      .then(itens => {
        this.modulos = [];
        for(var a of itens){
          this.modulos.push({ label: a, value: a });
        }
        this.modulos.unshift({ label: 'Todos', value: '' });
      })
      .catch(() => {
        this.modulos = [
          { label: 'Nenhum módulo cadastrado', value: '' }
        ];
      });
  }

  setPermissoes(){
    this.userTypeService.getPermissaoTipoUsuarioEdicao(this.idUserType, this.modulo)
    .then(permissions => {
      this.permissions = permissions;
    });
  }

  changeIdUserTipe(event: any){
    this.idUserType = event.value;
    this.setPermissoes();
  }

  changeModulo(event: any){
    this.modulo = event.value;
    this.setPermissoes();
  }

  changePermission(p: PermissionModelAux){
    this.userTypeService.changePermissao(this.idUserType, p.id, this.modulo)
    .then(permissions => {
      this.permissions = permissions;
    });
  }
}

export class PermissionModelAux {
  id: number;
  nome: string;
  modulo: string;
  descricao: string;
  vinculado: Boolean;
}