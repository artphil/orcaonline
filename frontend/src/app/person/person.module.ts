import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { TooltipModule } from 'primeng/tooltip';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { InputMaskModule } from 'primeng/inputmask';
import { DynamicDialogModule, DialogService } from 'primeng/dynamicdialog';

import { UserComponent, UserDialogComponent } from './user/user.component';
import { PermissaoComponent } from './permissao/permissao.component';
import { SharedModule } from './../shared/shared.module';

import { PermissionService } from './permissao/permission.service';
import { TipoUsuarioComponent } from './tipo-usuario/tipo-usuario.component';
import { UserService } from './user/user.service';
import { UserTypeService } from './tipo-usuario/user-type.service';
import { TypeUserPermissionComponent } from './type-user-permission/type-user-permission.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    UserComponent,
    UserListComponent,
    PermissaoComponent,
    TipoUsuarioComponent,
    TypeUserPermissionComponent,

    UserDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    HttpClientModule,

    InputTextModule,
    ButtonModule,
    DropdownModule,
    TableModule,
    InputMaskModule,
    TooltipModule,
    DynamicDialogModule,

    SharedModule
  ],
  entryComponents: [
    UserDialogComponent
  ],
  providers: [
    UserService,
    UserTypeService,
    PermissionService,

    DialogService
  ],
  exports: [
    UserComponent,
    PermissaoComponent,
    TipoUsuarioComponent
  ],
})
export class PersonModule { }
