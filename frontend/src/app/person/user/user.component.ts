import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute} from '@angular/router';

import { MessageService } from 'primeng/api';

import { UserModel, UserTypeModel} from '../person.model';
import { UserTypeService } from '../user-type.service';

import { UserService} from './user.service'

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user = new UserModel();
  userTypes: UserTypeModel[];

  idUser: number;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private userTypeService: UserTypeService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.idUser = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

  }

  consult(): void {
    if (!this.idUser) {
      this.user = new UserModel();
    } else {
      this.userService.getOne(this.idUser)
        .then((user: UserModel) => {
          this.user = user ? user : new UserModel();
        })
        .catch(() => {
          this.user = new UserModel();
        });
    }
  }

  getUserTypes(): void {
    this.userTypeService.getList()
      .then((userTypeList: UserTypeModel[]) => {
        this.userTypes = [];
        userTypeList.forEach(item => {
          this.userTypes.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.userTypes = [
          { label: 'Nenhum Tipo de Usuário cadastrado', value: null }
        ];
      });
  }

  saveUser(form: NgForm): void {
    if (!this.idUser) {
      this.userService.create(this.user)
        .then((user: UserModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: user.nomeFantasia });
          this.idUser = user.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar Usuário.', detail: msg });
        });
    }
    else {
      this.userService.update(this.user)
        .then((user: UserModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: user.nomeFantasia });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Usuário.', detail: msg });
        });
    }

  }

  clearUser(form: NgForm): void {
    form.reset();
    this.user = new UserModel();
    this.idUser = null;
  }

  removeUser(form: NgForm): void {
    this.userService.delete(this.idUser)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Usuário Excluido com Sucesso.', detail: `O id ${this.idUser} não pode mais ser acessado` }
        );
        this.clearUser(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Usuário.', detail: msg });
      });
  }

}


