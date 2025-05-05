import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { OpcaoRecuperaSenha } from '../login.model';
import { LoginService } from '../login.service';

@Component({
  selector: 'cmp-login-recuperar-senha',
  templateUrl: './login-recuperar-senha.component.html',
  styleUrl: './login-recuperar-senha.component.scss'
})
export class LoginRecuperarSenhaComponent implements OnInit {

  protected cpf: string;
  protected loading: boolean = false;
  protected selectedOpcao = new FormControl<OpcaoRecuperaSenha>(null);
  protected opcoes: OpcaoRecuperaSenha[];

  constructor(private service: LoginService, private router: Router, private acitvatedRoute: ActivatedRoute) {
    this.cpf = this.router.getCurrentNavigation()?.extras.state['cpf'];
    if (!this.cpf) {
      this.router.navigate(['/login']);
    }
  }
  public ngOnInit(): void {
    if (this.cpf) {
      this.loading = true;
      this.service.getOpcoesRecuperaSenha(this.cpf).subscribe(opcoes => {
        this.loading = false;
        this.opcoes = opcoes;
        if (this.opcoes.length > 0) {
          this.selectedOpcao.patchValue(this.opcoes[0]);
        }
      });
    }
  }

  protected continuar(): void {
    this.loading = true;
    this.service.enviarRecupercaoSenha(this.cpf, this.selectedOpcao.getRawValue()).subscribe(() => {
      this.router.navigate(['../recuperacao-senha-enviada'], { relativeTo: this.acitvatedRoute });
      this.loading = false;
    });
  }
}
