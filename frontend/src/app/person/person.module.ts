import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { TooltipModule } from 'primeng/tooltip';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { InputMaskModule } from 'primeng/inputmask/inputmask';

import { UserComponent } from './user/user.component';
import { PermissaoComponent } from './permissao/permissao.component';
import { SharedModule } from './../shared/shared.module';

import { PermissionService } from './permissao/permission.service';
import { UserService } from './user/user.service';
import { UserTypeService } from './user-type.service';

@NgModule({
  declarations: [
    UserComponent,
    PermissaoComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    InputTextModule,
    ButtonModule,
    DropdownModule,
    TableModule,
    InputMaskModule,

    SharedModule
  ],
  providers: [
    UserService,
    UserTypeService,
    TableModule,
    HttpClientModule,
    InputTextModule,
    ButtonModule,
    TooltipModule,
    DropdownModule,
    BrowserModule,
    BrowserAnimationsModule,
    ToastModule,
    SharedModule
  ],
  exports: [
    UserComponent,
    PermissaoComponent
  ],
  providers: [
    PermissionService
  ]
})
export class PersonModule { }
