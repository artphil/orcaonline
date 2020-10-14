import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { ProductModel } from 'src/app/product/product.model';
import { ProductService } from 'src/app/product/product/product.service';

import { BudgetItemModel, BudgetModel } from '../budget.model';
import { BudgetService } from './budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  idBudget: number;

  productList: SelectItem[];

  @Input() budget: BudgetModel;
  @Input() isPopup: boolean;
  @Output() savePopup = new EventEmitter<string>();

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private budgetServices: BudgetService,
    private productServices: ProductService
  ) { }

  ngOnInit(): void {
    if (this.isPopup) {
      this.idBudget = this.budget.id;
    } else {
      this.idBudget = Number(this.route.snapshot.paramMap.get('cod'));
      this.consult();
    }
  }

  consult(): void {

    if (!this.idBudget) {
      this.budget = new BudgetModel();
    } else {
      this.budgetServices.getOne(this.idBudget)
        .then((budget: BudgetModel) => {
          this.budget = budget ? budget : new BudgetModel();
        })
        .catch(() => {
          this.budget = new BudgetModel();
        });
    }
  }

  getProducts(): void {
    this.productServices.getList()
      .then((familyList: ProductModel[]) => {
        this.productList = [];
        familyList.forEach(f => {
          this.productList.push({ label: f.nome, value: f.id });
        });
      })
      .catch(() => {
        this.productList = [
          { label: 'Nenhuma Familia cadastrada', value: null }
        ];
      });
  }

  save(): void {
    this.savePopup.emit('value');

    if (this.idBudget) {
      this.budgetServices.update(this.budget)
        .then((budget: BudgetModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: budget.id.toString() });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Orçamento.', detail: msg });
        });
    }

  }
}

@Component({
  selector: 'app-dialog-budget',
  template: `
  <app-budget isPopup="true" [budget]='this.config.data.budget' (savePopup)="close($event)"></app-budget>
  `,
  styles: ['']
})
export class BudgetDialogComponent {

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) {  }

  close(e: string): void {
    this.ref.close(e);
  }
}
