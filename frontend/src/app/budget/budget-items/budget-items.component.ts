import { Component, OnInit } from '@angular/core';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { BudgetItemModel } from '../budget.model';

@Component({
  selector: 'app-budget-items',
  templateUrl: './budget-items.component.html',
  styleUrls: ['./budget-items.component.css']
})
export class BudgetItemsComponent implements OnInit {

  budgetItems: BudgetItemModel[];

  idBudget: number;

  constructor(
    public ref: DynamicDialogRef,
    public config: DynamicDialogConfig
  ) { }

  ngOnInit(): void {
    this.idBudget = this.config.data.id;
    this.budgetItems = this.config.data.items;
  }


}
