import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';

import { SharedModule } from './../shared/shared.module';

import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductService } from './product/product.service';

import { SegmentComponent } from './segment/segment.component';

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    SegmentComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    InputTextModule,
    ButtonModule,
	DropdownModule,
	TableModule,

    SharedModule
  ],
  exports: [],
  providers: [
    ProductService
  ]
})
export class ProductModule { }
