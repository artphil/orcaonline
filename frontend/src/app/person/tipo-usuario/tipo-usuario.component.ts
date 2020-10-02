import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';

import { UserTypeModel } from '../person.model';
import { UserTypeService } from './user-type.service';

@Component({
  selector: 'app-tipo-usuario',
  templateUrl: './tipo-usuario.component.html',
  styleUrls: ['./tipo-usuario.component.css']
})
export class TipoUsuarioComponent implements OnInit {

  tipo = new UserTypeModel();
  tipos = [];


  constructor(
    private userTypeService: UserTypeService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService
    ) { }

  ngOnInit(): void {
    this.consultar();
  }

  adicionar() {
    this.userTypeService.create(this.tipo)
        .then(tipo => {
          this.messageService.add(
            { severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: tipo.nome }
          );
          this.consultar();
          this.tipo = new UserTypeModel();
        })
        .catch(erro =>  {
          this.errorHandler.handle(erro);
        });
  }

  excluir(id: number) {
    this.userTypeService.delete(id)
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

  consultar(){
    this.userTypeService.getList()
    .then(tipos => {
      this.tipos = tipos;
    });
  }

  editar(t: UserTypeModel) {
    this.tipo = t;
  }

}
