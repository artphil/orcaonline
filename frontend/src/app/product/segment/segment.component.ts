import { Component, OnInit } from '@angular/core';
import { SegmentModel } from '../product.model';
import { SegmentService } from './segment.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-segment',
  templateUrl: './segment.component.html',
  styleUrls: ['./segment.component.css']
})
export class SegmentComponent implements OnInit {

  segment = new SegmentModel();

  idSegment: number;

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
