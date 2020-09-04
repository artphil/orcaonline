import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FamilyService {

  apiPath = 'http://45.80.152.3:8080/familias/';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  constructor(private http: HttpClient) { }

  getOne(query: any = ''): Promise<any> {

    return this.http.get<any>(`${this.apiPath}${query}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  getList(query: string = ''): Promise<any> {

    return this.http.get<any>(`${this.apiPath}${query}`, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  create(data: any): Promise<any> {

    return this.http.post<any>(this.apiPath, data, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  update(data: any): Promise<any> {

    return this.http.put<any>(`${this.apiPath}${data.id}`, data, this.httpOptions)
      .toPromise()
      .then(res => res);
  }

  delete(code: number): Promise<void> {

    return this.http.delete(`${this.apiPath}${code}`, this.httpOptions)
      .toPromise()
      .then(() => null);
  }

}
