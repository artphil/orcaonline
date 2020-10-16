import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class BudgetService {

  //apiPath = 'http://45.80.152.3:8080/orcamentos';
  apiPath: string;
  itemPath: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  constructor(
    private http: HttpClient,
    private errorHandler: ErrorHandlerService
    ) {
    this.apiPath = `${environment.apiUrl}/orcamentos`;
    this.itemPath = `${environment.apiUrl}/itemorcamentos`;
  }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  getList(query: string = ''): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${query}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  create(idMapa: number): Promise<any> {

    return this.http.post<any>(`${this.apiPath}/create/${idMapa}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  update(data: any): Promise<any> {

    return this.http.put<any>(`${this.apiPath}/${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  updateItem(data: any): Promise<any> {

    return this.http.put<any>(`${this.itemPath}/${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

  delete(code: number): Promise<void> {

    return this.http.delete(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(() => null)
      .catch(erro =>  {
        this.errorHandler.handle(erro);
      });
  }

}
