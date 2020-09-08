import { Component, OnInit } from '@angular/core';
import { FamilyModel, SegmentModel } from '../product.model';
import { SelectItem } from 'primeng/api/selectitem';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { FamilyService } from './family.service';
import { NgForm } from '@angular/forms';
import { DialogService } from 'primeng/dynamicdialog';
import { SegmentService } from '../segment/segment.service';
import { SegmentComponent } from '../segment/segment.component';

@Component({
  selector: 'app-family',
  templateUrl: './family.component.html',
  styleUrls: ['./family.component.css']
})
export class FamilyComponent implements OnInit {
  family = new FamilyModel();
  familySegments: SelectItem[];

  idFamily: number;
  isPopup: boolean;

  constructor(
    private route: ActivatedRoute,
    private familyServices: FamilyService,
    private segmentServices: SegmentService,
    private messageService: MessageService,
    private dialogService: DialogService,
  ) { }

  ngOnInit(): void {
    this.idFamily = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();
    this.getSegments();

  }

  getSegments(): void {
    this.segmentServices.getList()
      .then((segmentList: SegmentModel[]) => {
        this.familySegments = [];
        segmentList.forEach(item => {
          this.familySegments.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.familySegments = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    if (!this.idFamily) {
      this.family = new FamilyModel();
    } else {
      this.familyServices.getOne(this.idFamily)
        .then((family: FamilyModel) => {
          this.family = family ? family : new FamilyModel();
        })
        .catch(() => {
          this.family = new FamilyModel();
        });
    }
  }

  saveFamily(form: NgForm): void {
    if (!this.idFamily) {
      this.familyServices.create(this.family)
        .then((family: FamilyModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: family.nome });
          this.idFamily = family.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar Família.', detail: msg });
        });
    }
    else {
      this.familyServices.update(this.family)
        .then((family: FamilyModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: family.nome });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Família.', detail: msg });
        });
    }

  }

  clearFamily(form: NgForm): void {
    form.reset();
    this.family = new FamilyModel();
    this.idFamily = null;
  }

  removeFamily(form: NgForm): void {
    this.familyServices.delete(this.idFamily)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idFamily} não pode mais ser acessado` }
        );
        this.clearFamily(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Produto.', detail: msg });
      });
  }

  newSegment(): void {
    const ref = this.dialogService.open(SegmentComponent, {
      width: '50%'
    });

    ref.onClose.subscribe(() => this.getSegments());
  }

}
