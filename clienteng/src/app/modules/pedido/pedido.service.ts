import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_URL } from '../../../environments/environment';
import { HttpParamsObject } from '../../core/http/http-prams-object';
import { PedVndCardDto, PedVndProdCardDto } from './pedido-card/pedido-card.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private http: HttpClient) { }

  public getList(form: any): Observable<number[]> {
    return this.http.get<number[]>(`${API_URL}/pedido`, { params: new HttpParamsObject(form) });
  }

  public getPedVndCardDto(codPedVnd: number): Observable<PedVndCardDto> {
    return this.http.get<PedVndCardDto>(API_URL + "/pedido/card/" + codPedVnd);
  }

  public getPedVndProdCardDtoList(codPedVnd: number): Observable<PedVndProdCardDto[]> {
    return this.http.get<PedVndProdCardDto[]>(API_URL + "/pedido/card/prod/" + codPedVnd);
  }
}
