// Angular imports
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';

import { ToastModule } from 'primeng/toast';

// App imports
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ProductModule } from './product/product.module';
import { PersonModule } from './person/person.module';
import { SecurityModule } from './security/security.module';
import { BudgetModule } from './budget/budget.module'; 

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,

    ToastModule,

    CoreModule,
    SecurityModule,

    ProductModule,
    PersonModule,
    BudgetModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
