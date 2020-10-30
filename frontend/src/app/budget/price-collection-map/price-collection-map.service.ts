import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PriceCollectionMapService {

  //apiPath = 'http://45.80.152.3:8080/familias';
  apiPath: string;
  apiItemPath: string;

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
    this.apiPath = `${environment.apiUrl}/mapas`;
    this.apiItemPath = `${environment.apiUrl}/mapas/deleteItem`;
  }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  getList(query: string = ''): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${query}`, this.httpOptions)
      .toPromise()
      .then(res => res);
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

  getByFilterRunning(data: any): Promise<any> {
    let params = new HttpParams();

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

    return this.http.get<any>(`${this.apiPath}/filtrarEmAndamento`, { params, headers: this.headers } )
      .toPromise()
      .then(res => res)
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

  create(data: any): Promise<any> {
    return this.http.post<any>(this.apiPath, data, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  update(data: any): Promise<any> {
    return this.http.put<any>(`${this.apiPath}/${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  delete(code: number): Promise<void> {
    return this.http.delete(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(() => null);
  }

  getUnidades(): Promise<any> {
    return this.http.get<any>(`${this.apiPath}/unidades`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  addItem(data: any): Promise<any> {
    return this.http.post<any>(`${this.apiPath}/addItem`, data, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  deleteItem(code: number): Promise<any> {
    return this.http.put<any>(`${this.apiItemPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  closeMap(id: any): Promise<any> {
    return this.http.put<any>(`${this.apiPath}/encerrar/${id}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  startPriceCollection(id: any): Promise<any> {
    return this.http.put<any>(`${this.apiPath}/iniciarCotacao/${id}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  aproveBudget(idBudget: number): Promise<any> {
    return this.http.put<any>(`${this.apiPath}/aprovarOrcamento/${idBudget}`,  this.httpOptions)
      .toPromise()
      .then(res => res);
  }

}
