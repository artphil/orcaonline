import { MenuItem } from 'primeng/api';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'src/app/security/auth.service';
import { UserModel } from 'src/app/person/person.model';
import { UserService } from 'src/app/person/user/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  closed = true;
  user = new UserModel();

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
        routerLink: '/usr',
      },
      {
        label: 'Lista de Usuários',
        icon: 'pi pi-fw pi-align-justify',
        routerLink: '/usr/list'
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

  orcamento: MenuItem = {
    label: 'Orçamento',
    icon: 'pi pi-fw pi-plus',
    items: [

      {
        label: 'Lista de Mapas ',
        icon: 'pi pi-fw pi-align-justify',
        routerLink: '/bdt/map-list'

      }
    ]
  };


  orcCadastro: MenuItem = {
    label: 'Orçamentos',
    icon: 'pi pi-fw pi-plus',
    routerLink: '/bdt'
  };

  mapaColeta: MenuItem = {

    label: 'Mapa de coleta',
    icon: 'pi pi-fw pi-plus',
    routerLink: '/mapc',
  };


  constructor(
    private auth: AuthService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (this.auth.jwtPayload) {
      this.getUser();

      if (this.auth.hasPermission('ROLE_CADASTRAR_PRODUTO')) {
        this.prod.items.push(this.prodCadastro);
      }
      this.items.push(this.prod);
      if (this.auth.hasPermission('ROLE_CADASTRAR_USUARIO')) {
        this.items.push(this.users);
      }
      if (this.auth.hasPermission('ROLE_CADASTRAR_ORCAMENTO')) {
        // this.orcamento.items.push(this.orcCadastro);
        this.items.push(this.orcamento);
      }
      if (this.auth.hasPermission('ROLE_CADASTRAR_MAPACOLETA')) {
        this.items.push(this.mapaColeta);
      }
    }

  }

  getUser(): void {
    this.userService.getUser(this.auth.jwtPayload.user_name)
      .then((user: UserModel) => {
        this.user = user;
      });
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  logedUser(): boolean {
    return this.auth.jwtPayload?.user_name;
  }

  toggleMenu(): void {
    this.closed = !this.closed;
  }

}
