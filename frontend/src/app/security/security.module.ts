import { AuthService } from './auth.service';

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

import { LoginFormComponent } from './login-form/login-form.component';


@NgModule({
  declarations: [LoginFormComponent],
  imports: [
    CommonModule,
    InputTextModule,
    ButtonModule
  ],
  exports: [LoginFormComponent],
  providers: [AuthService]
})
export class SecurityModule { }
