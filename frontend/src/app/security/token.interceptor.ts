import { AuthService } from './auth.service';
import { Injectable } from '@angular/core';
import { HttpRequest,
         HttpHandler,
         HttpEvent,
         HttpInterceptor} from '@angular/common/http';
import { Observable } from 'rxjs';



@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(public auth: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(request.url.includes('/tiposUsuarios/tiposExternos') || request.url.includes('/usuarios/saveWithoutToken') ){
      return next.handle(request);
    } 
    request = request.clone({
      setHeaders: {
        Authorization: `${this.auth.getToken()}`
      }
    });
    return next.handle(request);
  }

}
