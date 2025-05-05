import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PedidoComponent } from './pedido.component';
import { PedidoDetalheComponent } from './pedido-detalhe/pedido-detalhe.component';

const routes: Routes = [
  {
    path: '',
    component: PedidoComponent
  },
  {
    path: ':codPedido',
    component: PedidoDetalheComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PedidoRoutingModule { }
