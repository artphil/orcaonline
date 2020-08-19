import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';

import { ProductComponent } from './product/product.component';

import { ProductService } from './product.service';

@NgModule({
  declarations: [
    ProductComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    InputTextModule,
    ButtonModule
  ],
  exports: [],
  providers: [
    ProductService
  ]
})
export class ProductModule { }
