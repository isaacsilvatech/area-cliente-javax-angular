import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ControleEnabledDirective } from './controle-enabled.directive';



@NgModule({
  exports: [ControleEnabledDirective],
  declarations: [ControleEnabledDirective],
  imports: [
    CommonModule
  ]
})
export class ControleEnabledModule { }
