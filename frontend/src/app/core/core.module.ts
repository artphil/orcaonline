import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from 'primeng/api';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from '../security/token.interceptor';

import { ButtonModule } from 'primeng/button';
import { TieredMenuModule } from 'primeng/tieredmenu';

import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';

import { AuthService } from '../security/auth.service';
import { ErrorHandlerService } from './error-handler.service';

@NgModule({
  declarations: [
    NavbarComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,

    ButtonModule,
    TieredMenuModule
  ],
  exports: [
    NavbarComponent,
    HomeComponent
  ],
  providers: [
    MessageService,
    ErrorHandlerService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
})
export class CoreModule { }
