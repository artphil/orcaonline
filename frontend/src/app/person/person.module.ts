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
import { PermissionComponent } from './permission/permission.component';
import { SharedModule } from './../shared/shared.module';

import { PermissionService } from './permission/permission.service';
import { UserTypeComponent } from './user-type/user-type.component';
import { UserService } from './user/user.service';
import { UserTypeService } from './user-type/user-type.service';
import { TypeUserPermissionComponent } from './type-user-permission/type-user-permission.component';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    UserComponent,
    UserListComponent,
    PermissionComponent,
    UserTypeComponent,
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
    PermissionComponent,
    UserTypeComponent
  ],
})
export class PersonModule { }
