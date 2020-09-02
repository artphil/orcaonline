import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';

import { SharedModule } from './../shared/shared.module';

import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { SegmentComponent } from './segment/segment.component';
import { FamilyComponent } from './family/family.component';
import { GtinComponent } from './gtin/gtin.component';

import { ProductService } from './product/product.service';
import { SegmentService } from './segment/segment.service';
import { GtinService } from './gtin/gtin.service';
import { DynamicDialogComponent } from './dynamic-dialog/dynamic-dialog.component';

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    SegmentComponent,
    FamilyComponent,
    GtinComponent,
    DynamicDialogComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,

    InputTextModule,
    ButtonModule,
    DropdownModule,
    TableModule,
    ToastModule,

    SharedModule
  ],
  exports: [],
  providers: [
    ProductService,
    SegmentService,
    GtinService
  ]
})
export class ProductModule { }
