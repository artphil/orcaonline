import { Component, Input, OnInit } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { DialogService } from 'primeng/dynamicdialog';
import { BudgetItemsComponent } from '../budget-items/budget-items.component';

import { BudgetFilterModel, BudgetItemModel, BudgetModel } from '../budget.model';
import { BudgetService } from '../budget/budget.service';

@Component({
  selector: 'app-budget-list',
  templateUrl: './budget-list.component.html',
  styleUrls: ['./budget-list.component.css']
})
export class BudgetListComponent implements OnInit {

  filter = new BudgetFilterModel();
  budgetList: BudgetModel[];

  statusList: SelectItem[];
  productList: SelectItem[];

  @Input() showAll = true;

  constructor(
    private dialogService: DialogService,
    private messageService: MessageService,
    private budgetService: BudgetService
  ) { }

  ngOnInit(): void {
    this.consult();
  }

  consult(): void {
    this.budgetService.getList()
      .then((budgets: BudgetModel[]) => {
        this.budgetList = budgets ? budgets : [];
      })
      .catch(() => {
        this.budgetList = [];
      });
  }

  showItems(id: number, items: BudgetItemModel[]): void {
    const ref = this.dialogService.open(BudgetItemsComponent, {
      width: '70%',
      data: { id, items }
    });
  }

}
