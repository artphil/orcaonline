import { ButtonModule } from 'primeng/button';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import {InputTextModule} from 'primeng/inputtext';

import { ProductComponent } from './product/product.component';

import { ProductService } from './product.service';

@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,

    InputTextModule,
    ButtonModule
  ],
  exports: [
    ProductComponent
  ],
  providers: [
    ProductService,
  ]
})
export class ProductModule { }
