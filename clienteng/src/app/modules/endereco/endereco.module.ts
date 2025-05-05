import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EnderecoRoutingModule } from './endereco-routing.module';
import { EnderecoComponent } from './endereco.component';


@NgModule({
  declarations: [
    EnderecoComponent
  ],
  imports: [
    CommonModule,
    EnderecoRoutingModule
  ]
})
export class EnderecoModule { }
