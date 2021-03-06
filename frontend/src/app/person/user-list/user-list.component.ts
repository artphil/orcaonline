import { Component, OnInit } from '@angular/core';

import { MessageService } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';

import { UserModel } from '../person.model';

import { UserDialogComponent } from '../user/user.component';
import { UserService } from '../user/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: UserModel[];

  constructor(
    private userService: UserService,
    private dialogService: DialogService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.consult();
  }

  consult(): void {
    this.userService.getList()
      .then((userList: UserModel[]) => {
        this.users = userList;
      })
      .catch(() => {
        this.users = [];
      });
  }

  newUser(): void {
    const ref = this.dialogService.open(UserDialogComponent, {
      width: '70%',
    });

    ref.onClose.subscribe(() => {
      setTimeout(() => { this.consult(); }, 300);
    });
  }

  editUser(id: number): void {
    const ref = this.dialogService.open(UserDialogComponent, {
      width: '70%',
      data: { id }
    });

    ref.onClose.subscribe(() => {
      setTimeout(() => { this.consult(); }, 300);
    });
  }

  removeUser(id: number): void {
    this.userService.delete(id)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Usuário Excluido com Sucesso.', detail: `O id ${id} não pode mais ser acessado` }
        );
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Usuário.', detail: msg });
      });
  }

}
