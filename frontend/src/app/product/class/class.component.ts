import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm, SelectMultipleControlValueAccessor } from '@angular/forms';

import { SelectItem, MessageService } from 'primeng/api';
import { DialogService, DynamicDialogRef, DynamicDialogConfig } from 'primeng/dynamicdialog';

import { ClassModel, FamilyModel } from '../product.model';
import { FamilyDialogComponent } from '../family/family.component';
import { ClassService } from './class.service';
import { FamilyService } from '../family/family.service';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit {
  pClass = new ClassModel();
  classFamilies: SelectItem[];

  idClass: number;

  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private classService: ClassService,
    private familyService: FamilyService,
    private messageService: MessageService,
    public dialogService: DialogService,
  ) { }

  ngOnInit(): void {
    this.idClass = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();
    this.getFamilies();
  }

  getFamilies(): void {
    this.familyService.getList()
      .then((familyList: FamilyModel[]) => {
        this.classFamilies = [];
        familyList.forEach(f => {
          this.classFamilies.push({ label: f.nome, value: f.id });
        });
      })
      .catch(() => {
        this.classFamilies = [
          { label: 'Nenhuma Familia cadastrada', value: null }
        ];
      });

  }

  consult(): void {
    if (!this.idClass) {
      this.pClass = new ClassModel();
    } else {
      this.classService.getOne(this.idClass)
        .then((clasItem: ClassModel) => {
          this.pClass = clasItem ? clasItem : new ClassModel();
        })
        .catch(() => {
          this.pClass = new ClassModel();
        });
    }
  }

  saveClass(form: NgForm): void {
    this.savePopup.emit('value');

    if (!this.idClass) {
      this.classService.create(this.pClass)
        .then((clasItem: ClassModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: clasItem.nome });
          this.idClass = clasItem.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar Classe.', detail: msg });
        });
    }
    else {
      this.classService.update(this.pClass)
        .then((clasItem: ClassModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: clasItem.nome });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Classe.', detail: msg });
        });
    }

  }

  clearClass(form: NgForm): void {
    form.reset();
    this.pClass = new ClassModel();
    this.idClass = null;
  }

  removeClass(form: NgForm): void {
    this.classService.delete(this.idClass)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Classe Excluida com Sucesso.', detail: `O id ${this.idClass} não pode mais ser acessado` }
        );
        this.clearClass(form);
      })
      .catch((err) => {
        const msg = err.error[0].mensagemUsuario;
        this.messageService.add({ severity: 'error', summary: 'Falha ao Excluir Classe.', detail: msg });
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

}


@Component({
  selector: 'app-dialog-class',
  template: `
  <app-class isPopup="true" (savePopup)="close($event)"></app-class>
  `,
  styles: ['']
})
export class ClassDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {
  }

  close(e: string): void {
    this.ref.close(e);
  }
}
