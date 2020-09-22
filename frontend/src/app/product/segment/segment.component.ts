import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { MessageService } from 'primeng/api';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';

import { SegmentModel } from '../product.model';

import { SegmentService } from './segment.service';

@Component({
  selector: 'app-segment',
  templateUrl: './segment.component.html',
  styleUrls: ['./segment.component.css']
})
export class SegmentComponent implements OnInit {

  segment = new SegmentModel();

  idSegment: number;

  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private segmentService: SegmentService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.idSegment = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

  }

  consult(): void {
    if (!this.idSegment) {
      this.segment = new SegmentModel();
    } else {
      this.segmentService.getOne(this.idSegment)
        .then((segment: SegmentModel) => {
          this.segment = segment ? segment : new SegmentModel();
        })
        .catch(() => {
          this.segment = new SegmentModel();
        });
    }
  }

  saveSegment(form: NgForm): void {
    this.savePopup.emit('value');
    if (!this.idSegment) {
      this.segmentService.create(this.segment)
        .then((segment: SegmentModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: segment.nome });
          this.idSegment = segment.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar Produto.', detail: msg });
        });
    }
    else {
      this.segmentService.update(this.segment)
        .then((segment: SegmentModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: segment.nome });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Produto.', detail: msg });
        });
    }

  }

  clearSegment(form: NgForm): void {
    form.reset();
    this.segment = new SegmentModel();
    this.idSegment = null;
  }

  removeSegment(form: NgForm): void {
    this.segmentService.delete(this.idSegment)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idSegment} não pode mais ser acessado` }
        );
        this.clearSegment(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Produto.', detail: msg });
      });
  }


}

@Component({
  selector: 'app-dialog-segment',
  template: `
  <app-segment isPopup="true" (savePopup)="close($event)"></app-segment>
  `,
  styles: ['']
})
export class SegmentDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
