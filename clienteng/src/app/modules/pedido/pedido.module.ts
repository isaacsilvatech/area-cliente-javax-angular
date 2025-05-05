import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PedidoRoutingModule } from './pedido-routing.module';
import { PedidoComponent } from './pedido.component';
import { ReactiveFormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { CalendarModule } from 'primeng/calendar';
import { ProximoCampoModule } from '../../shared/directives/proximo-campo/proximo-campo.module';
import { ButtonModule } from 'primeng/button';
import { InputNumberModule } from 'primeng/inputnumber';
import { CheckboxModule } from 'primeng/checkbox';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { PedidoCardComponent } from './pedido-card/pedido-card.component';
import { PedidoDetalheComponent } from './pedido-detalhe/pedido-detalhe.component';


@NgModule({
  declarations: [
    PedidoComponent,
    PedidoCardComponent,
    PedidoDetalheComponent
  ],
  imports: [
    CommonModule,
    PedidoRoutingModule,
    ReactiveFormsModule,
    InputTextModule,
    SidebarModule,
    CalendarModule,
    ProximoCampoModule,
    ButtonModule,
    InputNumberModule,
    NgxMaskDirective,
    NgxMaskPipe,
    CheckboxModule
  ]
})
export class PedidoModule { }
