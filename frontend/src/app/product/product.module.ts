import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ToastModule } from 'primeng/toast';
import { DynamicDialogModule, DialogService } from 'primeng/dynamicdialog';

import { SharedModule } from './../shared/shared.module';

import { ProductComponent } from './product/product.component';
import { ProductListComponent } from './product-list/product-list.component';
import { SegmentComponent, SegmentDialogComponent } from './segment/segment.component';
import { FamilyComponent, FamilyDialogComponent } from './family/family.component';
import { ClassComponent, ClassDialogComponent } from './class/class.component';
import { BrickComponent, BrickDialogComponent } from './brick/brick.component';
import { GtinComponent, GtinDialogComponent } from './gtin/gtin.component';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { ProductService } from './product/product.service';
import { SegmentService } from './segment/segment.service';
import { FamilyService } from './family/family.service';
import { ClassService } from './class/class.service';
import { BrickService } from './brick/brick.service';
import { GtinService } from './gtin/gtin.service';
import { NcmComponent, NcmDialogComponent } from './ncm/ncm.component';
import { NcmService } from './ncm/ncm.service';

@NgModule({
  declarations: [
    ProductListComponent,
    ProductComponent,
    SegmentComponent,
    FamilyComponent,
    GtinComponent,
    ClassComponent,
    BrickComponent,
    NcmComponent,

    SegmentDialogComponent,
    FamilyDialogComponent,
    ClassDialogComponent,
    BrickDialogComponent,
    GtinDialogComponent,
    NcmDialogComponent
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
    AutoCompleteModule,

    SharedModule
  ],
  exports: [
    ProductComponent,
    SegmentComponent,
    FamilyComponent,
    ClassComponent,
    BrickComponent,
    GtinComponent,
    NcmComponent
  ],
  entryComponents: [
  SegmentDialogComponent,
  FamilyDialogComponent,
  ClassDialogComponent,
  BrickDialogComponent,
  GtinDialogComponent,
  NcmDialogComponent
],
  providers: [
    ProductService,
    SegmentService,
    FamilyService,
    GtinService,
    ClassService,
    BrickService,
    NcmService,

    DialogService
  ]
})
export class ProductModule { }
