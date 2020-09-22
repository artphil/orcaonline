import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';
import { MessageService } from 'primeng/api';

import { NcmModel } from '../product.model';

import { NcmService } from './ncm.service';


@Component({
  selector: 'app-ncm',
  templateUrl: './ncm.component.html',
  styleUrls: ['./ncm.component.css']
})
export class NcmComponent implements OnInit {

  ncm = new NcmModel();

  idNcm: number;

  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

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
    this.savePopup.emit('value');

    if (!this.idNcm) {
      this.ncmService.create(this.ncm)
        .then((ncm: NcmModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: ncm.numero.toString() });
          this.idNcm = ncm.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar NCM.', detail: msg });
        });
    }
    else {
      this.ncmService.update(this.ncm)
        .then((ncm: NcmModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: ncm.numero.toString() });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar NCM.', detail: msg });
        });
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
          { severity: 'success', summary: 'NCM Excluido com Sucesso.', detail: `O id ${this.idNcm} não pode mais ser acessado` }
        );
        this.clearNcm(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir NCM.', detail: msg });
      });
  }


}


@Component({
  selector: 'app-dialog-ncm',
  template: `
  <app-ncm isPopup="true" (savePopup)="close($event)"></app-ncm>
  `,
  styles: ['']
})
export class NcmDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
