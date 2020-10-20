import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { MessageService } from 'primeng/api';
import { MenubarModule } from 'primeng/menubar';

import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { LoginFormComponent } from './login-form/login-form.component';

import { SharedModule } from '../shared/shared.module';
import { ErrorHandlerService } from './error-handler.service';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { BudgetModule } from '../budget/budget.module';

@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent,
    LoginFormComponent,
    ForbiddenComponent
  ],
  imports: [
    CommonModule,
    FormsModule,

    ButtonModule,
    InputTextModule,
    TieredMenuModule,
    MenubarModule,

    SharedModule,
    BudgetModule
  ],
  exports: [
    NavbarComponent,
    HomeComponent
  ],
  providers: [
    MessageService,

    ErrorHandlerService
  ],
})
export class CoreModule { }
