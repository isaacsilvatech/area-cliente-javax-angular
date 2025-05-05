import { Component } from '@angular/core';
import { STATUS_PEDIDO } from '../pedido-card/pedido-card.model';

@Component({
  selector: 'cmp-pedido-detalhe',
  templateUrl: './pedido-detalhe.component.html',
  styleUrl: './pedido-detalhe.component.scss'
})
export class PedidoDetalheComponent {

  pedVndDto: any;
   protected status = STATUS_PEDIDO;
}
