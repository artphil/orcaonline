import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { BudgetModel } from '../budget.model';
import { BudgetService } from './budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budget = new BudgetModel();

  idBudget: number;

  constructor(
    private route: ActivatedRoute,
    private messageService: MessageService,
    private budgetServices: BudgetService
  ) { }

  ngOnInit(): void {
    this.idBudget = Number(this.route.snapshot.paramMap.get('cod'));

    this.consult();
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

  save(): void {

    if (!this.idBudget) {
      this.budgetServices.create(this.budget)
        .then((budget: BudgetModel) => {
          this.messageService.add({ severity: 'success', summary: 'Cadastro Realizado com Sucesso.', detail: budget.id.toString() });
          this.idBudget = budget.id;
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Adicionar Família.', detail: msg });
        });
    }
    else {
      this.budgetServices.update(this.budget)
        .then((budget: BudgetModel) => {
          this.messageService.add({ severity: 'success', summary: 'Alteração Realizada com Sucesso.', detail: budget.id.toString() });
          this.consult();
        })
        .catch((err) => {
          const msg = err.error[0].mensagemUsuario;
          this.messageService.add({ severity: 'error', summary: 'Falha ao Alterar Família.', detail: msg });
        });
    }

  }
}
