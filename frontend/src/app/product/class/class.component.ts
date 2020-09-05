import { Component, OnInit } from '@angular/core';
import { ClassModel, FamilyModel } from '../product.model';
import { SelectItem, MessageService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassService } from './class.service';
import { FamilyService } from '../family/family.service';
import { NgForm } from '@angular/forms';
import { DialogService } from 'primeng/dynamicdialog';
import { FamilyComponent } from '../family/family.component';

@Component({
  selector: 'app-class',
  templateUrl: './class.component.html',
  styleUrls: ['./class.component.css']
})
export class ClassComponent implements OnInit {
  pClass: ClassModel;
  classFamilies: SelectItem[];

  isNewClass: boolean;
  idClass: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private classService: ClassService,
    private familyService: FamilyService,
    private messageService: MessageService,
    private dialogService: DialogService,
  ) { }

  ngOnInit(): void {
    this.idClass = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();

    this.familyService.getList()
      .then((familyList: FamilyModel[]) => {
        this.classFamilies = [];
        familyList.forEach( f => {
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
    this.isNewClass = !this.idClass;

    if (this.isNewClass) {
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
    if (this.isNewClass) {
      this.classService.create(this.pClass)
        .then((clasItem: ClassModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: clasItem.nome });
          this.router.navigateByUrl(`/pdt/cls/${clasItem.id}`);
        })
        .catch(() => alert('Solicitação não concluida.'));
    }
    else {
      this.classService.update(this.pClass)
        .then((clasItem: ClassModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: clasItem.nome });
          this.consult();
        })
        .catch(() => alert('Solicitação não concluida.'));
    }

  }

  clearClass(form: NgForm): void {
    form.reset();
    this.pClass = new ClassModel();
    this.router.navigateByUrl('/pdt/cls');
  }

  removeClass(form: NgForm): void {
    this.classService.delete(this.idClass)
      .then(() => {
        this.messageService.add(
          { severity: 'success', summary: 'Produto Excluido com Sucesso.', detail: `O id ${this.idClass} não pode mais ser acessado` }
          );
        this.router.navigateByUrl('/pdt/cls');
      })
      .catch(() => this.messageService.add(
        { severity: 'error', summary: 'Falha ao Excluir Produto.', detail: 'Id protegido ou inexistente' }
        )
      );
  }

  newFamily(): void {
    const ref = this.dialogService.open(FamilyComponent, {
      width: '50%'
    });
    ref.onClose.subscribe(() => this.consult);
  }

}
