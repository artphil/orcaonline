import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductComponent } from './product/product.component';

import { ProductService } from './product.service';

@NgModule({
  declarations: [ProductComponent

  ],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [
    ProductComponent
  ],
  providers: [
    ProductService
  ]
})
export class ProductModule { }
