/* Controle de rotas */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './core/home/home.component';
import { ProductComponent } from './product/product/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { UserComponent } from './person/user/user.component';
import { SegmentComponent } from './product/segment/segment.component';
import { FamilyComponent } from './product/family/family.component';
import { ClassComponent } from './product/class/class.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'usr', component: UserComponent },
  { path: 'pdt', component: ProductComponent },
  { path: 'pdt/list', component: ProductListComponent },
  { path: 'pdt/seg', component: SegmentComponent },
  { path: 'pdt/seg/:cod', component: SegmentComponent },
  { path: 'pdt/fam', component: FamilyComponent },
  { path: 'pdt/fam/:cod', component: FamilyComponent },
  { path: 'pdt/cls', component: ClassComponent },
  { path: 'pdt/cls/:cod', component: ClassComponent },
  { path: 'pdt/:cod', component: ProductComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
