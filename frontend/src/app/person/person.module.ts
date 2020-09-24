import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { InputMaskModule } from 'primeng/inputmask';

import { UserComponent } from './user/user.component';
import { SharedModule } from '../shared/shared.module';

import { UserService } from './user/user.service';
import { UserTypeService } from './user-type.service';



@NgModule({
  declarations: [
    UserComponent
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
    UserTypeService
  ]
})
export class PersonModule { }
