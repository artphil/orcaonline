import { Component, OnInit } from '@angular/core';

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


  constructor(private userTypeService: UserTypeService) { }

  ngOnInit(): void {
    this.consultar();
  }

  adicionar() {
    this.userTypeService.create(this.tipo)
        .then(tipo => {
          this.consultar();
          this.tipo = new UserTypeModel();
        });
  }

  excluir(id: number) {
    this.userTypeService.delete(id)
    .then(() => {
      this.consultar();
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
