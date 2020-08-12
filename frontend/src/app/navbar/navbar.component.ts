import { MenuItem } from 'primeng/api';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  closed = true;

  items: MenuItem[] = [
    {
      label: 'Inicio',
      icon: 'pi pi-fw pi-home',
      routerLink: '/'
    },
    {
      label: 'Produtos',
      icon: 'pi pi-fw pi-briefcase',
      items: [
        {
          label: 'Cadastro',
          icon: 'pi pi-fw pi-briefcase',
          routerLink: '/pdt'
        },
        {
          label: 'Lista',
          icon: 'pi pi-fw pi-th-large',
          routerLink: '/'
        }
      ]
    }
  ];

}
