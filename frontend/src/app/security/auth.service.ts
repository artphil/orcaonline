import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  oauthTokenUrl: string;
  // oauthTokenUrl = 'http://localhost:8080/oauth/token';
  tokensRevokeUrl: string;

  jwtPayload: any;
  helper = new JwtHelperService();
  constructor(private http: HttpClient) {
    this.oauthTokenUrl = `${environment.apiUrl}/oauth/token`;
    this.tokensRevokeUrl = `${environment.apiUrl}/tokens/revoke`;
    this.loadToken();
  }

  login(user: string, password: string) {
    this.clearToken();
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');
    const body = `username=${user}&password=${password}&grant_type=password`;

    return this.http.post<any>(this.oauthTokenUrl, body, { headers })
      .toPromise()
      .then(response => {
        console.log(response);
        this.saveToken(response.access_token);
      })
      .catch(response => {
        if (response.status === 400) {
          const responseJson = response.json();

          if (responseJson.error === 'invalid_grant') {
            return Promise.reject('Usuário ou senha inválida!');
          }
        }

        return Promise.reject(response);
      });
  }

  logout() {
    return this.http.delete(this.tokensRevokeUrl, { withCredentials: true })
      .toPromise()
      .then(() => {
        this.clearToken();
      });
  }

  public saveToken(token: string) {
    this.jwtPayload = this.helper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  public clearToken() {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }

  private loadToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.saveToken(token);

    }
  }

  public getToken(): string {
    const token = localStorage.getItem('token');
    if (token == null || token.length === 0) {
      return 'Basic YW5ndWxhcjpAbmd1bEByMA==';
    }
    return 'Bearer ' + localStorage.getItem('token');
  }
}
