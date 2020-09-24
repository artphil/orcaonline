/* Controle de rotas */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './core/home/home.component';
import { LoginFormComponent } from './core/login-form/login-form.component';
import { ForbiddenComponent } from './core/forbidden/forbidden.component';
import { UserComponent } from './person/user/user.component';
import { PermissaoComponent } from './person/permissao/permissao.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { ProductComponent } from './product/product/product.component';
import { SegmentComponent } from './product/segment/segment.component';
import { FamilyComponent } from './product/family/family.component';
import { ClassComponent } from './product/class/class.component';
import { BrickComponent } from './product/brick/brick.component';
import { GtinComponent } from './product/gtin/gtin.component';
import { NcmComponent } from './product/ncm/ncm.component';

import { AuthGuard } from './security/auth.guard';




const routes: Routes = [
  { path: 'login', component: LoginFormComponent },
  { path: 'forbidden', component: ForbiddenComponent },

  { path: '', component: HomeComponent},

  { path: 'usr', component: UserComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt', component: ProductComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/list', component: ProductListComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/seg', component: SegmentComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/seg/:cod', component: SegmentComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/fam', component: FamilyComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/fam/:cod', component: FamilyComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/cls', component: ClassComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/cls/:cod', component: ClassComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/brk', component: BrickComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/brk/:cod', component: BrickComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/gtn', component: GtinComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/gtn/:cod', component: GtinComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/ncm', component: NcmComponent, canActivate: [AuthGuard], data: { roles: [] } },
  { path: 'pdt/ncm/:cod', component: NcmComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'pdt/:cod', component: ProductComponent, canActivate: [AuthGuard], data: { roles: [] } },

  { path: 'per', component: PermissaoComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
