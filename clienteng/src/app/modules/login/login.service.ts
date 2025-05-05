import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../../../environments/environment';
import { LoginCheck, LoginToken, OpcaoRecuperaSenha } from './login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public check(login: LoginCheck): Observable<LoginToken> {
    return this.http.post<LoginToken>(`${API_URL}/login`, login);
  }

  public isCpfValido(cpf: string): Observable<boolean> {
    return this.http.get<boolean>(`${API_URL}/login/cpf-valido/${cpf}`);
  }

  public getOpcoesRecuperaSenha(cpf: string): Observable<OpcaoRecuperaSenha[]> {
    return this.http.get<OpcaoRecuperaSenha[]>(`${API_URL}/login/opcoes-recuperar-senha/${cpf}`);
  }

  public enviarRecupercaoSenha(cpf: string, opcao: OpcaoRecuperaSenha): Observable<void> {
    return this.http.post<void>(`${API_URL}/login/enviar-recuperacao-senha`, { cpf, opcao });
  }

  public alterarSenhaEsquecida(token: string, codPessoa: number, senha: string): Observable<string> {
    return this.http.post(`${API_URL}/login/alterar-senha-esquecida`, { codPessoa, senha }, { headers: { Authorization: `Bearer ${token}` }, responseType: 'text' });
  }

  public alterarSenha(codPessoa: number, senhaAtual: string, senhaNova: string): Observable<string> {
    return this.http.put(`${API_URL}/login/alterar-senha`, { codPessoa, senhaAtual, senhaNova }, { responseType: 'text' });
  }
}
