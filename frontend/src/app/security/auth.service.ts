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
    this.carregarToken();
  }

  login(usuario: string, senha: string) {
    this.limparToken();
    const headers =  new HttpHeaders()
                          .append('Content-Type', 'application/x-www-form-urlencoded')
                          .append('Authorization' , 'Basic YW5ndWxhcjpAbmd1bEByMA==' );
    const body = `username=${usuario}&password=${senha}&grant_type=password`;
    return this.http.post<any>(this.oauthTokenUrl, body, { headers } )
    .toPromise()
    .then(response => {
      this.armazenarToken(response.access_token);
    })
    .catch(response => {

    });
  }

  logout() {
    return this.http.delete(this.tokensRevokeUrl, {withCredentials: true})
      .toPromise()
      .then(() => {
      this.limparToken();
      });
  }

  public armazenarToken(token: string) {
   this.jwtPayload = this.helper.decodeToken(token);
   localStorage.setItem('token', token);
  }

  public limparToken() {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }

  private carregarToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);

    }
  }

  public getToken(): string {
    const token = localStorage.getItem('token');
    if (token  == null || token.length === 0) {
      return 'Basic YW5ndWxhcjpAbmd1bEByMA==';
    }
    return 'Bearer '  + localStorage.getItem('token');
  }
}
