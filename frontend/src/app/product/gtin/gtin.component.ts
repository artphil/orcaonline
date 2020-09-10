import { Component, OnInit, Inject, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { SelectItem, MessageService } from 'primeng/api';
import { DialogService, DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { GtinModel, BrickModel, ClassModel, FamilyModel, SegmentModel } from '../product.model';
import { BrickComponent, BrickDialogComponent } from '../brick/brick.component';
import { ClassComponent, ClassDialogComponent } from '../class/class.component';
import { FamilyComponent, FamilyDialogComponent } from '../family/family.component';
import { SegmentComponent, SegmentDialogComponent } from '../segment/segment.component';
import { ClassService } from '../class/class.service';
import { GtinService } from './gtin.service';
import { SegmentService } from '../segment/segment.service';
import { BrickService } from '../brick/brick.service';
import { FamilyService } from '../family/family.service';


@Component({
  selector: 'app-gtin',
  templateUrl: './gtin.component.html',
  styleUrls: ['./gtin.component.css']
})
export class GtinComponent implements OnInit {

  gtin: GtinModel;
  gtinBricks: SelectItem[];
  gtinClasses: SelectItem[];
  gtinFamilies: SelectItem[];
  gtinSegments: SelectItem[];

  idGtin: number;

  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private gtinServices: GtinService,
    private brickServices: BrickService,
    private classServices: ClassService,
    private familyServices: FamilyService,
    private segmentServices: SegmentService,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }


  ngOnInit(): void {

    this.idGtin = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.getBricks();
    this.getClasses();
    this.getFamilies();
    this.getSegments();
  }

  getBricks(): void {
    this.brickServices.getList()
      .then((brickList: BrickModel[]) => {
        this.gtinBricks = [];
        brickList.forEach(item => {
          this.gtinBricks.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinBricks = [
          { label: 'Nenhum Brick cadastrado', value: null }
        ];
      });
  }

  getClasses(): void {

    this.classServices.getList()
      .then((classList: ClassModel[]) => {
        this.gtinClasses = [];
        classList.forEach(item => {
          this.gtinClasses.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinClasses = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  getFamilies(): void {
    this.familyServices.getList()
      .then((familyList: FamilyModel[]) => {
        this.gtinFamilies = [];
        familyList.forEach(f => {
          this.gtinFamilies.push({ label: f.nome, value: f.id });
        });
      })
      .catch(() => {
        this.gtinFamilies = [
          { label: 'Nenhuma Familia cadastrada', value: null }
        ];
      });
  }

  getSegments(): void {
    this.segmentServices.getList()
      .then((segmentList: SegmentModel[]) => {
        this.gtinSegments = [];
        segmentList.forEach(item => {
          this.gtinSegments.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.gtinSegments = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    if (!this.idGtin) {
      this.gtin = new GtinModel();
    } else {
      this.gtinServices.getOne(this.idGtin)
        .then((gtin: GtinModel) => {
          this.gtin = gtin ? gtin : new GtinModel();
        })
        .catch(() => {
          this.gtin = new GtinModel();
        });
    }
  }

  saveGtin(form: NgForm): void {
    this.savePopup.emit('value');

    if (!this.idGtin) {
      this.gtinServices.create(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add(
            { severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: gtin.numero.toString() }
          );
          this.idGtin = gtin.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Salvar Gtin.', detail: msg });
        });
    }
    else {
      this.gtinServices.update(this.gtin)
        .then((gtin: GtinModel) => {
          this.messageService.add(
            { severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: gtin.numero.toString() }
          );
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Gtin.', detail: msg });
        });
    }

  }

  clearGtin(form: NgForm): void {
    form.reset();
    this.gtin = new GtinModel();
    this.idGtin = undefined;
  }

  removeGtin(form: NgForm): void {
    this.gtinServices.delete(this.idGtin)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idGtin} não pode mais ser acessado` }
        );
        this.clearGtin(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Produto.', detail: msg });
      });
  }

  newBrick(): void {
    const ref = this.dialogService.open(BrickDialogComponent, {
      width: '50%'
    });
    ref.onClose.subscribe(() => {
      setTimeout(() => { this.getBricks(); }, 300);
    });
  }

  newClass(): void {
    const ref = this.dialogService.open(ClassDialogComponent, {

      width: '50%'
    });
    ref.onClose.subscribe(() => {
      setTimeout(() => { this.getClasses(); }, 300);
    });
  }

  newFamily(): void {
    const ref = this.dialogService.open(FamilyDialogComponent, {
      width: '50%'
    });
    ref.onClose.subscribe(() => {
      setTimeout(() => { this.getFamilies(); }, 300);
    });
  }

  newSegment(): void {
    const ref = this.dialogService.open(SegmentDialogComponent, {
      width: '50%'
    });

    ref.onClose.subscribe(() => {
      setTimeout(() => { this.getSegments(); }, 300);
    });
  }

}

@Component({
  selector: 'app-dialog-gtin',
  template: `
  <app-gtin isPopup="true" (savePopup)="close($event)"></app-gtin>
  `,
  styles: ['']
})
export class GtinDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
