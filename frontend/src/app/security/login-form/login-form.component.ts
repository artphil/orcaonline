import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';

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
    private errorHandler: ErrorHandlerService
  ) { }

  login() {
    this.auth.login(this.user, this.pass)
    .then(() => {
      this.router.navigate(['/']);
    })
    .catch((erro) =>  {
      this.errorHandler.handle(erro);
    });
  }

}
