import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';

import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  user: string;
  pass: string;

  constructor(
    private auth: AuthService,
    private router: Router,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService
  ) { }

  login(form: NgForm): void {
    if (form.valid) {
      this.auth.login(this.user, this.pass)
      .then(() => {
        this.router.navigate(['/']);
      })
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });

      this.pass = '';
    } else {
      this.messageService.add(
        { severity: 'error', summary: 'Falha ao enviar.', detail: 'Pereencha os campos corretamente.' }
      );
    }
  }

}
