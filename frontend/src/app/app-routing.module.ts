/* Controle de rotas */

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './core/home/home.component';
import { ProductComponent } from './product/product/product.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import { UserComponent } from './people/user/user.component';

const routes: Routes = [
	{ path: '', component: HomeComponent },
	{ path: 'usr', component: UserComponent },
	{ path: 'pdt', component: ProductComponent },
	{ path: 'pdt/list', component: ProductListComponent },
	{ path: 'pdt/:cod', component: ProductComponent }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
