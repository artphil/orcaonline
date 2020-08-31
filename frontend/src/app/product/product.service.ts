import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  apiPath = 'http://45.80.152.3:8080/produtos/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  constructor(private http: HttpClient) { }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}${code}`)
      .toPromise()
      .then(res => { return res });
  }

  getList(): Promise<any> {

    return this.http.get<any>(this.apiPath, this.httpOptions)
      .toPromise()
      .then(res => { return res });
  }

  create(data: any): Promise<any> {

    delete data.ncm;
    delete data.gtin;

    return this.http.post<any>(this.apiPath, data, this.httpOptions)
      .toPromise()
      .then(res => { return res });
  }

  update(data: any): Promise<any> {

    delete data.ncm;
    delete data.gtin;

    console.log('to send:', data);
    return this.http.put<any>(`${this.apiPath}${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => { return res });
  }

  delete(code: number): Promise<void> {

    return this.http.delete(`${this.apiPath}${code}`, this.httpOptions)
      .toPromise()
      .then(() => null);
  }

}
