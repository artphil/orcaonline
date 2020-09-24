import { MenuItem } from 'primeng/api';
import { Component, OnInit } from '@angular/core';

import { AuthService } from 'src/app/security/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  closed = true;
  items: MenuItem[] = [{
    label: 'Início',
    icon: 'pi pi-fw pi-home',
    routerLink: '/'
  }];

  constructor(
    private auth: AuthService
  ) { }

  ngOnInit(): void {

    if (this.auth.jwtPayload)
    this.items.push(
      {
        label: 'Produtos',
        icon: 'pi pi-fw pi-shopping-cart',
        items: [
          {
            label: 'Cadastro',
            icon: 'pi pi-fw pi-plus',
            routerLink: '/pdt',
          },
          {
            label: 'Lista',
            icon: 'pi pi-fw pi-th-large',
            routerLink: '/pdt/list'
          }
        ]
      }
    );
    this.items.push(
      {
        label: 'Usuários',
        icon: 'pi pi-fw pi-users',
        items: [
          {
            label: 'Cadastro',
            icon: 'pi pi-fw pi-plus',
            routerLink: '/pdt',
          },
          {
            label: 'Permissão',
            icon: 'pi pi-fw pi-th-large',
            routerLink: '/per'
          }
        ]
      }
    );

  }

  logout() {
    this.auth.logout();
  }
}
