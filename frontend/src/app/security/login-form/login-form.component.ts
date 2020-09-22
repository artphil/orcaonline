import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

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
    private router: Router
  ) { }

  login() {
    this.auth.login(this.user, this.pass)
    .then(() => {
      this.router.navigate(['/']);
    })
    .catch();
  }

}
