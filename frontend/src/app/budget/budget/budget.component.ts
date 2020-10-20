import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { ProductModel } from 'src/app/product/product.model';
import { ProductService } from 'src/app/product/product/product.service';

import { BudgetItemModel, BudgetModel } from '../budget.model';
import { BudgetService } from './budget.service';

class Ssee {label: string; value: number; }

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  idBudget: number;

  productList = {};
  listaAux: SelectItem[];

  itemsModifieds = {};

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

          this.budget.itens.forEach(i => {
            if (!i.produto) {
              i.produto = new ProductModel();
              this.getProducts(i.itemMapa.brick.id);
            }
          });
        })
        .catch(() => {
          this.budget = new BudgetModel();
        });
    }
  }

  getProducts(itemId: number): void {
    if (!this.productList[itemId]){
      this.productList[itemId] = [];
    }

    this.productServices.getByBricks(itemId)
      .then((products: ProductModel[]) => {
        products.forEach(f => {
          this.productList[itemId].push({ label: f.nome, value: f.id });
        });
        console.log( this.productList);
      })
      .catch(() => {
        this.productList[itemId].push(
          { label: 'Nenhum Produto cadastrado', value: null }
        );
      });
  }

  send(): void {
    this.savePopup.emit('value');

    if (this.budget.status.nome === 'Aberto') {
      this.budgetServices.send(this.budget.id)
        .then((budget: BudgetModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: budget.id.toString() });
          this.budget = budget;
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Orçamento.', detail: msg });
        });
    } else {
      this.messageService.add({
        severity: 'error',
        summary: 'Falha ao Alterar Orçamento.',
        detail: 'Este orçamento não está apto para ser enviado.'
      });
    }

  }

  modified(item: BudgetItemModel): void {
    const idItem = item.id.toString();
    this.itemsModifieds[idItem] = true;
  }

  isModified(item: BudgetItemModel): boolean {
    const idItem = item.id.toString();
    return this.itemsModifieds[idItem];
  }

  valid(item: BudgetItemModel): boolean {
    if (this.budget.status.id === 1 && item.produto?.id && item.valorUnitario > 0 && item.valorUnitarioPrazo > 0) {
      return true;
    }
    return false;
  }

  saveItem(data: BudgetItemModel): void {
    const idItem = data.id.toString();

    if (this.valid(data)) {
      this.budgetServices.updateItem(data)
        .then(() => {
          this.itemsModifieds[idItem] = false;
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: 'Novo valor salvo.' });
          this.consult();
        })
        .catch(() => {
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Orçamento.', detail: 'Tente novamente.' });
        });
    } else {
      this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Orçamento.', detail: 'Valor unitário inválido' });
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
  ) { }

  close(e: string): void {
    this.ref.close(e);
  }
}
