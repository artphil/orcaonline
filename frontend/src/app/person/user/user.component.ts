import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { MessageService } from 'primeng/api';
import { SelectItem } from 'primeng/api/selectitem';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';

import { UserModel, UserTypeModel } from '../person.model';
import { UserTypeService } from '../user-type/user-type.service';

import { UserService } from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user = new UserModel();
  userTypes: SelectItem[];

  idUser: number;
  isNewUser:boolean;

  @Input() userId: number;
  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private userTypeService: UserTypeService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.idUser = Number(this.route.snapshot.paramMap.get('cod'));
    console.log(this.route.snapshot.url.toString());
    this.isNewUser = (this.route.snapshot.url.toString() === 'sing-in');

    if (this.userId) {
      this.idUser = this.userId;
      console.log(this.idUser);
    }

    this.consult();
    this.getUserTypes();
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

    if (this.isNewUser){
      this.router.navigate(['/login'])
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


@Component({
  selector: 'app-dialog-user',
  template: `
  <app-user isPopup="true" [userId]="userId" (savePopup)="close($event)"></app-user>
  `,
  styles: ['']
})
export class UserDialogComponent {

  userId: number;

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
    if (this.config.data) {
      this.userId = this.config.data.id;
    }
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
