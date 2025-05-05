import { Component } from '@angular/core';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';

@Component({
  selector: 'cmp-erro-dialog',
  templateUrl: './erro-dialog.component.html',
  styleUrl: './erro-dialog.component.scss'
})
export class ErroDialogComponent {

  protected msg: string = '';

  constructor(protected ref: DynamicDialogRef, private config: DynamicDialogConfig) {
    this.msg = this.config.data.msg;
  }
}
