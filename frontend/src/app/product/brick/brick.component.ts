import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';

import { SelectItem, MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';

import { BrickModel, ClassModel } from '../product.model';
import { ClassComponent, ClassDialogComponent } from '../class/class.component';
import { BrickService } from './brick.service';
import { ClassService } from '../class/class.service';

@Component({
  selector: 'app-brick',
  templateUrl: './brick.component.html',
  styleUrls: ['./brick.component.css']
})
export class BrickComponent implements OnInit {

  brick: BrickModel;
  brickClasses: SelectItem[];

  idBrick: number;
  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private brickServices: BrickService,
    private classServices: ClassService,
    private messageService: MessageService,
    private dialogService: DialogService
  ) { }

  ngOnInit(): void {
    this.idBrick = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();
    this.getClasses();

  }

  getClasses(): void {
    this.classServices.getList()
      .then((classList: ClassModel[]) => {
        this.brickClasses = [];
        classList.forEach(item => {
          this.brickClasses.push({ label: item.nome, value: item.id });
        });
      })
      .catch(() => {
        this.brickClasses = [
          { label: 'Nenhum Segmento cadastrado', value: null }
        ];
      });
  }

  consult(): void {
    if (!this.idBrick) {
      this.brick = new BrickModel();
    } else {
      this.brickServices.getOne(this.idBrick)
        .then((brick: BrickModel) => {
          this.brick = brick ? brick : new BrickModel();
        })
        .catch(() => {
          this.brick = new BrickModel();
        });
    }
  }

  saveBrick(form: NgForm): void {
    this.savePopup.emit('value');

    if (!this.idBrick) {
      this.brickServices.create(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: brick.nome });
          this.idBrick = brick.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Salvar Brick.', detail: msg });
        });
    }
    else {
      this.brickServices.update(this.brick)
        .then((brick: BrickModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: brick.nome });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Brick.', detail: msg });
        });
    }
  }

  clearBrick(form: NgForm): void {
    form.reset();
    this.brick = new BrickModel();
    this.idBrick = undefined;
  }

  removeBrick(form: NgForm): void {
    this.brickServices.delete(this.idBrick)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Brick Excluido com Sucesso.', detail: `O id ${this.idBrick} não pode mais ser acessado` }
        );
        this.clearBrick(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Brick.', detail: msg });
      });
  }

  newClass(): void {
    const ref = this.dialogService.open(ClassDialogComponent, {
      data: { popup: true },
       width: '50%'
    });
    ref.onClose.subscribe(() => {
      setTimeout(() => { this.getClasses(); }, 300);
    });
  }

}

@Component({
  selector: 'app-dialog-brick',
  template: `
  <app-brick isPopup="true" (savePopup)="close($event)"></app-brick>
  `,
  styles: ['']
})
export class BrickDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
