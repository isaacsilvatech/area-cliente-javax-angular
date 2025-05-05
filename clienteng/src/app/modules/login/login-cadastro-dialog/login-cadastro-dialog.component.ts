import { Component } from '@angular/core';
import { LojaService } from '../../../core/service/loja.service';

@Component({
  selector: 'cmp-login-cadastro-dialog',
  templateUrl: './login-cadastro-dialog.component.html',
  styleUrl: './login-cadastro-dialog.component.scss'
})
export class LoginCadastroDialogComponent {

  constructor(private service: LojaService) { }

  protected irParaLojas() {
    this.service.getEnderecos().subscribe(url => {
      window.open(url, '_blank');
    });
  }
}
