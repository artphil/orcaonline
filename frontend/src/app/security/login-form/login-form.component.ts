import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  login(email: string, senha: string) {
    this.auth.login(email, senha);
    this.router.navigate(['/']);
  }

}
