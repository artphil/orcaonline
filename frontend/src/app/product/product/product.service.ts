import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { environment } from 'src/environments/environment';

import { ProductFilterModel } from '../product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //apiPath = 'http://45.80.152.3:8080/produtos';
  apiPath: string;

  headers = new HttpHeaders({
    'Content-Type': 'application/json; charset=utf-8'
  });

  constructor(private http: HttpClient) {
    this.apiPath = `${environment.apiUrl}/produtos`;
  }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${code}`, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  getByBricks(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/bricks/${code}`, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  getList(): Promise<any> {

    return this.http.get<any>(this.apiPath, { headers: this.headers })
      .toPromise()
      .then(res => res);
  }

  getFilteredList(query: ProductFilterModel): Promise<any> {
    const urlPath = this.apiPath + '/pesquisar';
    let params = new HttpParams();

    if (query.nome) {
      params = params.append('nome', query.nome);
    }
    if (query.segmento) {
      params = params.append('segmento', query.segmento.toString());
    }
    if (query.familia) {
      params = params.append('familia', query.familia.toString());
    }
    if (query.classe) {
      params = params.append('classe', query.classe.toString());
    }
    if (query.brick) {
      params = params.append('brick', query.brick.toString());
    }


    return this.http.get<any>(urlPath, { params, headers: this.headers })
      .toPromise()
      .then(res => res);
  }

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

}
