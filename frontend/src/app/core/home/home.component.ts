import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/person/person.model';
import { UserService } from 'src/app/person/user/user.service';

import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: UserModel;

  constructor(
    private router: Router,
    private auth: AuthService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    if (!this.logedUser()) {
      this.router.navigate(['/login']);
    }
    this.getUser();
  }

  getUser(): void {
    this.userService.getUser(this.auth.jwtPayload.user_name)
      .then((user: UserModel) => {
        this.user = user;
      });
  }

  logedUser(): boolean {
    return this.auth.jwtPayload?.user_name;
  }

  hasBudgets(): boolean {
    return true;
  }

  hasProposals(): boolean {
    return true;
  }
}
