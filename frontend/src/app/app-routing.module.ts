/* Controle de rotas */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './core/home/home.component';
import { LoginFormComponent } from './core/login-form/login-form.component';
import { ForbiddenComponent } from './core/forbidden/forbidden.component';

import { UserComponent } from './person/user/user.component';
import { UserListComponent } from './person/user-list/user-list.component';
import { PermissionComponent } from './person/permission/permission.component';
import { UserTypeComponent } from './person/user-type/user-type.component';

import { ProductComponent } from './product/product/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { SegmentComponent } from './product/segment/segment.component';
import { FamilyComponent } from './product/family/family.component';
import { ClassComponent } from './product/class/class.component';
import { BrickComponent } from './product/brick/brick.component';
import { GtinComponent } from './product/gtin/gtin.component';
import { NcmComponent } from './product/ncm/ncm.component';

import { PriceCollectionMapComponent } from './budget/price-collection-map/price-collection-map.component';
import { BudgetComponent } from './budget/budget/budget.component';

import { AuthGuard } from './security/auth.guard';
import { TypeUserPermissionComponent } from './person/type-user-permission/type-user-permission.component';
import { PriceMapListComponent } from './budget/price-map-list/price-map-list.component';
import { BudgetListComponent } from './budget/budget-list/budget-list.component';

const routes: Routes = [
  { path: 'login', component: LoginFormComponent },
  { path: 'sing-in', component: UserComponent },

  { path: 'forbidden', component: ForbiddenComponent },

  { path: '', component: HomeComponent },

  { path: 'usr/new', component: UserComponent },
  { path: 'usr', component: UserComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_USUARIO'] } },
  { path: 'usr/list', component: UserListComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_USUARIO'] } },

  { path: 'pdt', component: ProductComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/list', component: ProductListComponent },

  { path: 'pdt/seg', component: SegmentComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/seg/:cod', component: SegmentComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/fam', component: FamilyComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/fam/:cod', component: FamilyComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/cls', component: ClassComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/cls/:cod', component: ClassComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/brk', component: BrickComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/brk/:cod', component: BrickComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/gtn', component: GtinComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/gtn/:cod', component: GtinComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/ncm', component: NcmComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },
  { path: 'pdt/ncm/:cod', component: NcmComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'pdt/:cod', component: ProductComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_PRODUTO'] } },

  { path: 'per', component: PermissionComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_USUARIO'] } },
  { path: 'tipousr', component: UserTypeComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_USUARIO'] } },
  {
    path: 'tipousr-per/:cod', component: TypeUserPermissionComponent,
    canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_USUARIO'] }
  },

  { path: 'mapc', component: PriceCollectionMapComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_MAPACOLETA'] }  },
  { path: 'mapc/:cod', component: PriceCollectionMapComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_MAPACOLETA'] }  },

  { path: 'bdt/map-list', component: PriceMapListComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_ORCAMENTO'] } },
  { path: 'bdt/list', component: BudgetListComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_ORCAMENTO'] }  },
  { path: 'bdt/:cod', component: BudgetComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_CADASTRAR_ORCAMENTO'] }  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
