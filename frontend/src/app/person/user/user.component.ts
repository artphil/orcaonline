import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { UserModel} from './user.model';
import { UserService} from './user.service'
import { MessageService } from 'primeng/api';
import { ActivatedRoute} from '@angular/router';
import { NgForm } from '@angular/forms';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user = new UserModel();

  idUser: number;
  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();


  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
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


export class UserDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
