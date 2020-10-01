import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private router: Router,
    private auth: AuthService
  ) { }

  ngOnInit(): void {
    if (!this.logedUser()) {
      this.router.navigate(['/login']);
    }

  }

  logedUser(): boolean{
    return this.auth.jwtPayload?.user_name;
  }
}
