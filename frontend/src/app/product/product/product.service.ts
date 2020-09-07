import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //apiPath = 'http://45.80.152.3:8080/produtos';
  apiPath: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  constructor(private http: HttpClient) {
    this.apiPath = `${environment.apiUrl}/produtos`;
  }

  getOne(code: number = null): Promise<any> {

    return this.http.get<any>(`${this.apiPath}/${code}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  getList(): Promise<any> {

    return this.http.get<any>(this.apiPath, this.httpOptions)
      .toPromise()
      .then(res => res);
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

}
