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
  private refreshTokenTimeout: any;

  constructor(private http: HttpClient) {
    this.oauthTokenUrl = `${environment.apiUrl}/oauth/token`;
    this.tokensRevokeUrl = `${environment.apiUrl}/tokens/revoke`;
    this.loadToken();
  }

  login(user: string, password: string): Promise<void> {
    this.clearToken();
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');
    const body = `username=${user}&password=${password}&grant_type=password`;

    return this.http.post<any>(this.oauthTokenUrl, body, { headers, withCredentials: true })
      .toPromise()
      .then(response => {
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

  refreshToken(): Promise<void> {
    this.clearToken();
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded')
      .append('Authorization', 'Basic YW5ndWxhcjpAbmd1bEByMA==');
    const body = `grant_type=refresh_token`;

    return this.http.post<any>(this.oauthTokenUrl, body, { headers, withCredentials: true })
      .toPromise()
      .then(response => {
        this.saveToken(response.access_token);
        return Promise.resolve(null);
      })
      .catch(response => {
        console.log('Erro ao renovar token', response);
        this.clearToken();
        return Promise.resolve(null);
      });
  }


  private startRefreshTokenTimer(): void {
    const expires = new Date(this.jwtPayload.exp);
    const timeout = expires.getTime() * 1000 - Date.now();
    console.log('tempo:', expires.getTime(), Date.now(), timeout)
    this.refreshTokenTimeout = setTimeout(() => this.refreshToken().then(), timeout);
  }

  private stopRefreshTokenTimer(): void {
    clearTimeout(this.refreshTokenTimeout);
  }

  logout(): void {
    this.http.delete(this.tokensRevokeUrl, { withCredentials: true });
    this.stopRefreshTokenTimer();
    this.clearToken();
  }

  public saveToken(token: string): void {
    this.jwtPayload = this.helper.decodeToken(token);
    this.startRefreshTokenTimer();
    localStorage.setItem('token', token);
  }

  public clearToken(): void {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }

  private loadToken(): void {
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

  hasPermission(role: string): boolean {
    console.log(this.jwtPayload.authorities)
    return this.jwtPayload.authorities && this.jwtPayload.authorities.includes(role);
  }

  hasAnyPermission(roles: Array<string>): boolean {
    let containsRole = false;

    roles.forEach(role => {
      if (this.hasPermission(role)) {
        containsRole = true;
      }
    });
    return containsRole;
  }
}
