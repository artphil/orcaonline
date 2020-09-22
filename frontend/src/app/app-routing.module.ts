/* Controle de rotas */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './core/home/home.component';
import { UserComponent } from './person/user/user.component';
import { ProductComponent } from './product/product/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { SegmentComponent } from './product/segment/segment.component';
import { FamilyComponent } from './product/family/family.component';
import { ClassComponent } from './product/class/class.component';
import { BrickComponent } from './product/brick/brick.component';
import { GtinComponent } from './product/gtin/gtin.component';
import { NcmComponent } from './product/ncm/ncm.component';
import { LoginFormComponent } from './core/login-form/login-form.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginFormComponent },

  { path: 'usr', component: UserComponent },
  { path: 'pdt', component: ProductComponent },
  { path: 'pdt/list', component: ProductListComponent },

  { path: 'pdt/seg', component: SegmentComponent },
  { path: 'pdt/seg/:cod', component: SegmentComponent },

  { path: 'pdt/fam', component: FamilyComponent },
  { path: 'pdt/fam/:cod', component: FamilyComponent },

  { path: 'pdt/cls', component: ClassComponent },
  { path: 'pdt/cls/:cod', component: ClassComponent },

  { path: 'pdt/brk', component: BrickComponent },
  { path: 'pdt/brk/:cod', component: BrickComponent },

  { path: 'pdt/gtn', component: GtinComponent },
  { path: 'pdt/gtn/:cod', component: GtinComponent },

  { path: 'pdt/ncm', component: NcmComponent },
  { path: 'pdt/ncm/:cod', component: NcmComponent },

  { path: 'pdt/:cod', component: ProductComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
