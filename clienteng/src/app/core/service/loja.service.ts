import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LojaService {

  constructor(private http: HttpClient) { }

  public getEnderecos(): Observable<string> {
    return this.http.get(`${API_URL}/loja/enderecos`, { responseType: 'text' });
  }
}
