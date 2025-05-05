import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ResumoRoutingModule } from './resumo-routing.module';
import { ResumoComponent } from './resumo.component';


@NgModule({
  declarations: [
    ResumoComponent
  ],
  imports: [
    CommonModule,
    ResumoRoutingModule
  ]
})
export class ResumoModule { }
