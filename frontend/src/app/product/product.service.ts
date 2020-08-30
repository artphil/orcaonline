import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  apiPath = 'http://45.80.152.3:8080/produtos/';
  mockyPath = 'https://run.mocky.io/v3/e09f62b3-7450-49f1-b66b-033d675621db';

  httpOptions = {
    headers: new HttpHeaders({
      // 'Access-Control-Allow-Origin': 'http://127.0.0.1:3000',
      'Content-Type': 'application/json; charset=utf-8'
    })
  };

  constructor(private http: HttpClient) { }

  getOne(code: number): Promise<any> {
    let path = this.apiPath
    if (code) {
      path += code.toString();
    }
    return this.http.get<any>(path)
      .toPromise()
      .then(res => { return res });
  }

  getList(): Promise<any> {
    return this.http.get<any>(this.apiPath)
      .toPromise()
      .then(res => { return res });
  }

  create(body: any): Promise<any> {
    return this.http.post(<any>this.apiPath, body)
      .toPromise()
      .then(res => { return res });
  }

  update(data: any): Promise<any> {
    let path = this.apiPath
    path += data.id.toString();

    let body = JSON.stringify(data);
    // delete body.id;
    console.log('to send:', body);
    return this.http.put<any>(path, body, this.httpOptions)
      .toPromise()
      .then(res => { return res });
  }

  delete(code: number): Promise<void> {
    let path = this.apiPath + code.toString();

    return this.http.delete<any>(path)
      .toPromise()
      .then(() => null);
  }

}
