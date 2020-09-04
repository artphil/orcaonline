import { Component, OnInit } from '@angular/core';
import { FamilyModel, SegmentModel } from '../product.model';
import { SelectItem } from 'primeng/api/selectitem';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { FamilyService } from './family.service';
import { NgForm } from '@angular/forms';
import { SegmentService } from '../segment/segment.service';

@Component({
  selector: 'app-family',
  templateUrl: './family.component.html',
  styleUrls: ['./family.component.css']
})
export class FamilyComponent implements OnInit {
  family: FamilyModel;
  familySegments: SelectItem[];

  isNewFamily: boolean;
  idFamily: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private familyServices: FamilyService,
    private segmentServices: SegmentService,
    private messageService: MessageService,
  ) { }

  ngOnInit(): void {
    this.idFamily = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.segmentServices.getList()
      .then((segmentList: SegmentModel[]) => {
        this.familySegments = [];
        segmentList.forEach(s => {
          this.familySegments.push({ label: s.nome, value: s.id });
        });
      })
      .catch(() => {
        this.familySegments = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    this.isNewFamily = !this.idFamily;

    if (this.isNewFamily) {
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
    if (this.isNewFamily) {
      this.familyServices.create(this.family)
        .then((family: FamilyModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: family.nome });
          this.router.navigateByUrl(`/pdt/fam/${family.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.familyServices.update(this.family)
        .then((family: FamilyModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: family.nome });
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearFamily(form: NgForm): void {
    form.reset();
    this.family = new FamilyModel();
    this.router.navigateByUrl('/pdt/fam');
  }

  removeFamily(form: NgForm): void {
    this.familyServices.delete(this.idFamily)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idFamily} não pode mais ser acessado` }
        );
        this.router.navigateByUrl('/pdt/fam');
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
      )
      );
  }

  newSegment(): void {
    this.router.navigateByUrl('/pdt/seg/');
  }

}
