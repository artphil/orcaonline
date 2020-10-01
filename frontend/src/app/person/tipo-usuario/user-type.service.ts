import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from 'src/app/security/auth.service';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserTypeService {

  //apiPath = 'http://45.80.152.3:8080/tiposUsuarios';
  apiPath: string;

  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient, private auth: AuthService) {
    this.apiPath = `${environment.apiUrl}/tiposUsuarios`;
  }

  getOne(code: number = null): Promise<any> {
    return this.http.get<any>(`${this.apiPath}/${code}`, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  getList(query: string = ''): Promise<any> {
    let url = this.logedUser() ? this.apiPath : `${this.apiPath}/tiposExternos`;
    return this.http.get<any>(url, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  // getListExternos(query: string = ''): Promise<any> {
  //   return this.http.get<any>(`${this.apiPath}/tiposExternos`, { headers: this.headers })
  //     .toPromise()
  //     .then(res => res);
  // }

  create(data: any): Promise<any> {
    return this.http.post<any>(this.apiPath, data, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  update(data: any): Promise<any> {
    return this.http.put<any>(`${this.apiPath}/${data.id}`, data, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  delete(code: number): Promise<void> {
    return this.http.delete(`${this.apiPath}/${code}`, { headers: this.headers })
      .toPromise()
      .then(() => null);
  }

  getPermissaoTipoUsuarioEdicao(code: string, modulo: string){
    const urlPath = this.apiPath + '/permissoesTipoUsuarioEdicao';
    let params = new HttpParams();
    params = params.append('idTipoUsuario', code);
    params = params.append('modulo', modulo);
    return this.http.get<any>(urlPath, {params, headers: this.headers })
    .toPromise()
    .then(res => res);
  }

  changePermissao(codeUserType: string, codePermissio: any, modulo: string){
    const urlPath = this.apiPath + '/changePermissao';
    let params = new HttpParams();
    params = params.append('idTipoUsuario', codeUserType);
    params = params.append('idPermissao', codePermissio);
    params = params.append('modulo', modulo);
    return this.http.get<any>(urlPath, {params, headers: this.headers })
    .toPromise()
    .then(res => res);
  }

  getModalidades(): Promise<any> {
    return this.http.get<any>(`${this.apiPath}/modalidades`, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  logedUser(): boolean{
    return this.auth.jwtPayload?.user_name;
  }

}
