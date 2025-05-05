import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

export const ACCESS_TOKEN_STORAGE_KEY = "access_token";

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private jwt = new JwtHelperService();

  constructor() {
    if (this.isTokenValido()) {
      let token: string = this.getToken();
      this.setToken(token);
    }
  }

  public setToken(token: string) {
    window.localStorage.setItem(ACCESS_TOKEN_STORAGE_KEY, token);
  }

  public getCodPessoa(token: string): number {
    return this.jwt.decodeToken(token)["codPessoa"]
  }

  public getToken(): string {
    return window.localStorage.getItem(ACCESS_TOKEN_STORAGE_KEY) ?? '';
  }

  public removeToken() {
    window.localStorage.removeItem(ACCESS_TOKEN_STORAGE_KEY);
  }

  public isTokenValido(): boolean {
    let token = this.getToken();
    return token != null && token != 'null' && !this.jwt.isTokenExpired(token);
  }

  public isValido(token: string): boolean {
    return token != null && token != 'null' && !this.jwt.isTokenExpired(token);
  }

  public isLogado(): boolean {
    return !!this.getToken();
  }
}
