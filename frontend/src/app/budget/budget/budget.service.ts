import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  //apiPath = 'http://45.80.152.3:8080/orcamentos';
  apiPath: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  headers = new HttpHeaders({
    'Content-Type': 'application/json; charset=utf-8'
  });

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlerService
  ) {
    this.apiPath = `${environment.apiUrl}/orcamentos`;
  }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  getList(query: string = ''): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${query}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  getByFilter(data: any): Promise<any> {
    let params = new HttpParams();

    if (data.idStatus) {
      params = params.append('idStatus', data.idStatus);
    }
    if (data.dataRegistroInicial) {
      params = params.append('dataRegistroInicial', data.dataRegistroInicial.toLocaleDateString());
    }
    if (data.dataRegistroFinal) {
      params = params.append('dataRegistroFinal', data.dataRegistroFinal.toLocaleDateString());
    }
    if (data.dataEnvioInicial) {
      params = params.append('dataEnvioInicial', data.dataEnvioInicial.toLocaleDateString());
    }
    if (data.dataEnvioFinal) {
      params = params.append('dataEnvioFinal', data.dataEnvioFinal.toLocaleDateString());
    }

    return this.http.get<any>(`${this.apiPath}/filtrar`, { params, headers: this.headers } )
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  create(idMapa: number): Promise<any> {

    return this.http.post<any>(`${this.apiPath}/create/${idMapa}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  update(data: any): Promise<any> {

    return this.http.put<any>(`${this.apiPath}/${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  send(idBudget: any): Promise<any> {

    return this.http.put<any>(`${this.apiPath}/enviar/${idBudget}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  updateItem(data: any): Promise<any> {
    const dataSend = {
      id: data.id,
      valorUnitario: data.valorUnitario,
      valorUnitarioPrazo: data.valorUnitarioPrazo,
      produto: { id: data.produto.id }
    };

    return this.http.post<any>(`${this.apiPath}/updateItem`, dataSend, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  delete(code: number): Promise<void> {

    return this.http.delete(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(() => null)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

}
