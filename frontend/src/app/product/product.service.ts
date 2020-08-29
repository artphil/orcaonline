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
		  'Content-Type':  'application/json'
		})
	  }; 
	  
	constructor(private http: HttpClient) { }

	getOne(code: number): Promise<any> {
		let path = this.apiPath
		if (code) {
			// path += '/';
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
		return this.http.post(this.apiPath, JSON.stringify(body))
			.toPromise()
			.then(res => { return res });
	}

	update(code: number, body: any): Promise<any> {
		let path = this.apiPath
		if (code) {
			path += code.toString();
		}
		delete body.segmento;
		delete body.classe;
		delete body.familia;
		delete body.id;
		delete body.ncm;
		delete body.gtin;
		console.log(body);
		return this.http.put(path, JSON.stringify(body), this.httpOptions)
			.toPromise()
			.then(res => { return res });
	}
}
