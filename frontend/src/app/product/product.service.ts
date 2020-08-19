import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  apiPath = 'http://45.80.152.3:8080/produtos';
  mockyPath = 'https://run.mocky.io/v3/e09f62b3-7450-49f1-b66b-033d675621db';

  constructor(private http: HttpClient) { }

  getOne(): Promise<any> {
    return this.http.get<any>(this.apiPath)
      .toPromise()
      .then(res => res[0]);
  }

  getList(): Promise<any> {
    return this.http.get<any>(this.apiPath)
      .toPromise();
  }
}
