import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { API_URL } from '../../../environments/environment';
import { HTTP_PRIORITY, HTTP_PRIORITY_NO_DESTROY } from '../http/http-destroyer.interceptor';
import { AuthService } from './auth.service';
import { PessoaLogada } from '../model/pessoa-logada.model';

@Injectable({
  providedIn: 'root'
})
export class PessoaLogadaService {

  private pessoaLogadaSubject = new BehaviorSubject<PessoaLogada>(null);

  constructor(private auth: AuthService, private http: HttpClient) {
    this.reloadPessoaLogada();
  }

  public reloadPessoaLogada() {
    if (this.auth.isTokenValido()) {
      let params = new HttpParams().append(HTTP_PRIORITY, HTTP_PRIORITY_NO_DESTROY);
      this.http.get<PessoaLogada>(`${API_URL}/pessoa`, { params }).subscribe(pessoa => {
        this.pessoaLogadaSubject.next(pessoa);
      })
    }
  }

  public removePessoaLogada(): void {
    this.pessoaLogadaSubject.next(null);
  }

  public getPessoaLogada(): Observable<PessoaLogada> {
    return this.pessoaLogadaSubject.asObservable();
  }
}
