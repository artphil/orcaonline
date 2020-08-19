import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MsgValidationComponent } from './msg-validation/msg-validation.component';


@NgModule({
  declarations: [
    MsgValidationComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    MsgValidationComponent
  ]
})
export class SharedModule { }
