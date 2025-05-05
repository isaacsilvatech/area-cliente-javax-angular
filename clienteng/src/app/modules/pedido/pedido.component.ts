import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { TITLE } from '../../../environments/environment';
import { PedidoService } from './pedido.service';

@Component({
  selector: 'cmp-pedido',
  templateUrl: './pedido.component.html',
  styleUrl: './pedido.component.scss'
})
export class PedidoComponent implements OnInit {

  private hoje = new Date();
  private umMesAtras = new Date(new Date().setMonth(this.hoje.getMonth() - 1));

  protected form = new FormGroup({
    codPedVnd: new FormControl<number>(null),
    dataDe: new FormControl<Date | string>(this.datePipe.transform(this.umMesAtras, 'dd/MM/yyyy')),
    dataAte: new FormControl<Date | string>(this.datePipe.transform(this.hoje, 'dd/MM/yyyy')),
    gravado: new FormControl<boolean>(true),
    enviado: new FormControl<boolean>(true),
    pago: new FormControl<boolean>(true),
    despachado: new FormControl<boolean>(true),
    entregue: new FormControl<boolean>(true),
    finalizado: new FormControl<boolean>(false),
    cancelado: new FormControl<boolean>(false),
    expirado: new FormControl<boolean>(false),
  });

  protected filtroVisivel: boolean = false;
  protected lista: number[] = [];

  constructor(private title: Title, private datePipe: DatePipe, private service: PedidoService) {
    this.title.setTitle(`Pedidos - ${TITLE}`);
  }

  public ngOnInit(): void {
    this.filtrar();
  }
  protected filtrar(): void {
    this.filtroVisivel = false;
    this.lista = [];
    this.service.getList(this.form.getRawValue()).subscribe(lista => {
      this.lista = lista;
    })
  }
}
