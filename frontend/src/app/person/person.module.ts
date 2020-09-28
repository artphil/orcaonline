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

import { UserComponent } from './user/user.component';
import { PermissaoComponent } from './permissao/permissao.component';
import { SharedModule } from './../shared/shared.module';

import { PermissionService } from './permissao/permission.service';
import { TipoUsuarioComponent } from './tipo-usuario/tipo-usuario.component';
import { UserService } from './user/user.service';
import { UserTypeService } from './tipo-usuario/user-type.service';
import { TypeUserPermissionComponent } from './type-user-permission/type-user-permission.component';

@NgModule({
  declarations: [
    UserComponent,
    PermissaoComponent,
    TipoUsuarioComponent,
    TypeUserPermissionComponent
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
    TooltipModule,
    SharedModule,
    RouterModule
  ],
  providers: [
    UserService,
    UserTypeService,
    PermissionService
  ],
  exports: [
    UserComponent,
    PermissaoComponent,
    TipoUsuarioComponent
  ],
})
export class PersonModule { }
