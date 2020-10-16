import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AuthService } from 'src/app/security/auth.service';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

   //apiPath = 'http://45.80.152.3:8080/usuarios';
   apiPath: string;
   userPath: string;

   httpOptions = {
     headers: new HttpHeaders({
       'Content-Type': 'application/json; charset=utf-8'
     })
   };

   constructor(private http: HttpClient, private auth: AuthService) {
     this.apiPath = `${environment.apiUrl}/usuarios`;
     this.userPath = `${this.apiPath}/getByEmail`;
   }

   getUser(email: string): Promise<any> {
    return this.http.get<any>(`${this.userPath}/${email}`, this.httpOptions)
      .toPromise()
      .then(res => res)
      .catch(() => {
        console.log('ninguem logado');
      });
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

   create(data: any): Promise<any> {
     let url = this.logedUser() ? this.apiPath : `${this.apiPath}/saveWithoutToken`;
     return this.http.post<any>(url, data, this.httpOptions)
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

   logedUser(): boolean{
    return this.auth.jwtPayload?.user_name;
  }
}
