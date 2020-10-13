import { Injectable } from '@angular/core';

import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(private messageService: MessageService) { }

  handle(errorResponse: any): void {
    let msg: string;

    if (typeof errorResponse === 'string') {
      msg = errorResponse;
    } else {
      msg = 'Erro ao processar serviço remoto. Tente novamente.';

      if (errorResponse instanceof Response && errorResponse.status >= 400 && errorResponse.status <= 499) {
        let errors: any;
        try {
          errors = errorResponse.json();
          msg = errors[0].mensagemUsuario;
        } catch (e) { }
      }
      console.log('Erro na requisição:', errorResponse);
    }

    this.messageService.add(
      { severity: 'error', summary: 'Erro', detail: msg }
    );
  }
}
