import { Component, OnInit } from '@angular/core';
import { NcmModel } from '../product.model';
import { ActivatedRoute } from '@angular/router';
import { NcmService } from './ncm.service';
import { MessageService } from 'primeng/api';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-ncm',
  templateUrl: './ncm.component.html',
  styleUrls: ['./ncm.component.css']
})
export class NcmComponent implements OnInit {

  ncm = new NcmModel();

  idNcm: number;

  constructor(
    private route: ActivatedRoute,
    private ncmService: NcmService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.idNcm = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

  }

  consult(): void {
    if (!this.idNcm) {
      this.ncm = new NcmModel();
    } else {
      this.ncmService.getOne(this.idNcm)
        .then((ncm: NcmModel) => {
          this.ncm = ncm ? ncm : new NcmModel();
        })
        .catch(() => {
          this.ncm = new NcmModel();
        });
    }
  }

  saveNcm(form: NgForm): void {
    if (!this.idNcm) {
      this.ncmService.create(this.ncm)
        .then((ncm: NcmModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: ncm.numero.toString() });
          this.idNcm = ncm.id;
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.ncmService.update(this.ncm)
        .then((ncm: NcmModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: ncm.numero.toString() });
          this.consult();
        })
        .catch(() => this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Produto.', detail: 'Id protegido ou inexistente' }));
    }

  }

  clearNcm(form: NgForm): void {
    form.reset();
    this.ncm = new NcmModel();
    this.idNcm = undefined;
  }

  removeNcm(form: NgForm): void {
    this.ncmService.delete(this.idNcm)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idNcm} não pode mais ser acessado` }
        );
        this.clearNcm(form);
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
      )
      );
  }


}
