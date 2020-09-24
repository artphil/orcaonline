import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { TooltipModule} from 'primeng/tooltip';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

import { SharedModule } from './../shared/shared.module';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { UserComponent } from './user/user.component';
import { PermissaoComponent } from './permissao/permissao.component';

import { PermissionService } from './permissao/permission.service';
import { TipoUsuarioComponent } from './tipo-usuario/tipo-usuario.component';

@NgModule({
  declarations: [
    UserComponent,
    PermissaoComponent,
    TipoUsuarioComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
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
    PermissaoComponent,
    TipoUsuarioComponent
  ],
  providers: [
    PermissionService
  ]
})
export class PersonModule { }
