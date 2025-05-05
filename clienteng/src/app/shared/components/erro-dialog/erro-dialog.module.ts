import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ErroDialogComponent } from './erro-dialog.component';
import { ButtonModule } from 'primeng/button';



@NgModule({
  declarations: [
    ErroDialogComponent
  ],
  imports: [
    CommonModule,
    ButtonModule
  ]
})
export class ErroDialogModule { }
