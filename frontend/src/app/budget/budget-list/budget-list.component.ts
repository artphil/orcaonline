import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService, SelectItem } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { BudgetItemsComponent } from '../budget-items/budget-items.component';

import { BudgetFilterModel, BudgetItemModel, BudgetModel, StatusModel } from '../budget.model';
import { BudgetService } from '../budget/budget.service';

@Component({
  selector: 'app-budget-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.css']
})
export class BudgetListComponent implements OnInit {

  filter = new BudgetFilterModel();
  budgetList: BudgetModel[];

  statusList = StatusModel.selectItems();
  productList: SelectItem[];

  @Input() showAll = true;

  constructor(
    private router: Router,
    private dialogService: DialogService,
    private budgetService: BudgetService
  ) { }

  ngOnInit(): void {
    this.consult();
  }

  consult(): void {
    this.budgetService.getByFilter(this.filter)
      .then((budgets: BudgetModel[]) => {
        if (budgets) {
          budgets.forEach((budget) => {
            budget.dataRegistro = new Date(budget.dataRegistro);
          });
          this.budgetList = budgets;
        } else {
          this.budgetList = [];
        }
      })
      .catch(() => {
        this.budgetList = [];
      });
  }

  edit(id: number): void {
    this.router.navigate([`/bdt/${id}`]);
  }

  showItems(id: number, items: BudgetItemModel[]): void {
    const ref = this.dialogService.open(BudgetItemsComponent, {
      width: '70%',
      data: { id, items }
    });
  }

}
