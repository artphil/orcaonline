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
import { FamilyService } from './family/family.service';
import { ClassService } from './class/class.service';
import { GtinService } from './gtin/gtin.service';
import { DynamicDialogComponent } from './dynamic-dialog/dynamic-dialog.component';
import { DynamicDialogModule, DialogService } from 'primeng/dynamicdialog';
import { ClassComponent } from './class/class.component';

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    SegmentComponent,
    FamilyComponent,
    GtinComponent,
    DynamicDialogComponent,
    ClassComponent
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
    DynamicDialogModule,

    SharedModule
  ],
  exports: [],
  providers: [
    ProductService,
    SegmentService,
    FamilyService,
    GtinService,
    ClassService,
    DialogService
  ]
})
export class ProductModule { }
