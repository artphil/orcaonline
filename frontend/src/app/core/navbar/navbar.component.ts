import { MenuItem } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  prod: MenuItem = {
    label: 'Produtos',
    icon: 'pi pi-fw pi-shopping-cart',
    items: [
      {
        label: 'Lista',
        icon: 'pi pi-fw pi-th-large',
        routerLink: '/pdt/list'
      }
    ]
  };

  prodCadastro: MenuItem = {
    label: 'Cadastro',
    icon: 'pi pi-fw pi-plus',
    items: [
      {
        label: 'Produtos',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt',
      },
      {
        label: 'NCM',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/ncm',
      },
      {
        label: 'Segmentos',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/seg',
      },
      {
        label: 'Familia',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/fam',
      },
      {
        label: 'Classe',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/cls',
      },
      {
        label: 'Bricks',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/brk',
      },
      {
        label: 'GTIN',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt/gtn',
      }
    ]
  };

  users: MenuItem = {
    label: 'Usuários',
    icon: 'pi pi-fw pi-plus',
    items: [
      {
        label: 'Usuário',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/pdt',
      },
      {
        label: 'Tipo de Usuário',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/tipousr'
      },
      {
        label: 'Permissão',
        icon: 'pi pi-fw pi-plus',
        routerLink: '/per'
      }
    ]
  };

  constructor(
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.auth.jwtPayload) {
      this.prod.items.push(this.prodCadastro);
      this.items.push(this.prod);
      this.items.push(this.users);
    }


    // this.items.push(
    //   {
    //     label: 'Usuários',
    //     icon: 'pi pi-fw pi-users',
    //     items: [
    //       {
    //         label: 'Cadastro',
    //         icon: 'pi pi-fw pi-plus',
    //         routerLink: '/pdt',
    //       },
    //       {
    //         label: 'Tipo de Usuário',
    //         icon: 'pi pi-fw pi-th-large',
    //         routerLink: '/tipousr'
    //       },
    //       {
    //         label: 'Permissão',
    //         icon: 'pi pi-fw pi-th-large',
    //         routerLink: '/per'
    //       }
    //     ]
    //   }
    // );

  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  logedUser(): boolean{
    return this.auth.jwtPayload?.user_name;
  }

}
