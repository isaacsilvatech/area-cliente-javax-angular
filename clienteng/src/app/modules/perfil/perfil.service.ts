import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PessoaDados } from './perfil.model';
import { API_URL } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  constructor(private http: HttpClient) { }

  public getDados(): Observable<PessoaDados> {
    return this.http.get<PessoaDados>(`${API_URL}/perfil`);
  }

  public salvar(dados: PessoaDados): Observable<PessoaDados> {
    return this.http.put<PessoaDados>(`${API_URL}/perfil`, dados);
  }
}
