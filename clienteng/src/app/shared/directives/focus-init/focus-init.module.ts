import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FocusInitDirective } from './focus-init.directive';



@NgModule({
  exports: [
    FocusInitDirective
  ],
  declarations: [
    FocusInitDirective
  ],
  imports: [
    CommonModule
  ]
})
export class FocusInitModule { }
