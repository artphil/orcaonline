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
import { SegmentComponent } from './segment/segment.component';
import { FamilyComponent } from './family/family.component';
import { BrickComponent } from './brick/brick.component';
import { ClassComponent } from './class/class.component';
import { GtinComponent } from './gtin/gtin.component';
import { DynamicDialogComponent } from './dynamic-dialog/dynamic-dialog.component';
import { AutoCompleteModule } from 'primeng/autocomplete';

import { ProductService } from './product/product.service';
import { SegmentService } from './segment/segment.service';
import { FamilyService } from './family/family.service';
import { ClassService } from './class/class.service';
import { BrickService } from './brick/brick.service';
import { GtinService } from './gtin/gtin.service';
import { NcmComponent } from './ncm/ncm.component';
import { NcmService } from './ncm/ncm.service';

@NgModule({
  declarations: [
    ProductComponent,
    ProductListComponent,
    SegmentComponent,
    FamilyComponent,
    GtinComponent,
    DynamicDialogComponent,
    ClassComponent,
    BrickComponent,
    NcmComponent
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
  exports: [],
  entryComponents: [ClassComponent],
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
