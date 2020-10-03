import { Component, OnInit } from '@angular/core';

import { BudgetModel } from '../orcamento.model';


@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  budget = new BudgetModel();

  idBudget: number;

  constructor() { }

  ngOnInit(): void {
  }

  salvar(): void { }

}
