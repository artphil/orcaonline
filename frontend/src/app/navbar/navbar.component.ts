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
      // routerLink: '/'
    },
    {
      label: 'Edit',
      icon: 'pi pi-fw pi-pencil',
      items: [
        { label: 'Delete', icon: 'pi pi-fw pi-trash' },
        { label: 'Refresh', icon: 'pi pi-fw pi-refresh' }
      ]
    }
  ];

  showHiddeMenu() {
    this.closed = !this.closed;
  }
}
