import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserComponent } from './user/user.component';
import { PermissaoComponent } from './permissao/permissao.component';



@NgModule({
  declarations: [
    UserComponent,
    PermissaoComponent
  ],
  imports: [
    CommonModule
  ]
})
export class PersonModule { }
