import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { TITLE } from '../../../../environments/environment';
import { ErroDialogComponent } from '../../../shared/components/erro-dialog/erro-dialog.component';
import { DialogService } from 'primeng/dynamicdialog';
import { LoginCadastroDialogComponent } from '../login-cadastro-dialog/login-cadastro-dialog.component';
import { AuthService } from '../../../core/service/auth.service';
import { PessoaLogadaService } from '../../../core/service/pessoa-logada.service';

@Component({
  selector: 'cmp-login-form',
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.scss'
})
export class LoginFormComponent {

  protected form = new FormGroup({
    login: new FormControl<string>(null, [Validators.required, Validators.minLength(11)]),
    senha: new FormControl<string>(null, [Validators.required])
  });

  protected loading: boolean = false;

  protected senhaIvalida: boolean = false;
  protected clienteNaoCadastrado: boolean = false;

  constructor(
    private title: Title,
    private service: LoginService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private dialog: DialogService,
    private auth: AuthService,
    private pessoaLogada: PessoaLogadaService
  ) {
    this.title.setTitle(`Login - ${TITLE}`);
  }

  protected entrar() {
    if (this.validaTela()) {
      this.loading = true;
      this.service.check(this.form.getRawValue()).subscribe((loginToken) => {
        
        if (loginToken.msg === 'ok') {
          this.auth.setToken(loginToken.token);
          this.pessoaLogada.reloadPessoaLogada();
          this.router.navigate(['home'], { relativeTo: this.activatedRoute });

        } else if (loginToken.msg === 'SENHA_INVALIDA') {
          this.senhaIvalida = true;

        } else if (loginToken.msg == 'CLIENTE_NAO_ENCONTRADO' || loginToken.msg == 'CLIENTE_INATIVO' || loginToken.msg == 'SENHA_NAO_CADASTRADA') {
          this.dialog.open(LoginCadastroDialogComponent, {
            header: 'Ops!',
            closable: true,
            style: { width: '100%', maxWidth: '450px', margin: '1rem' }
          });

        } else {
          this.dialog.open(ErroDialogComponent, { header: 'Ops!', style: { maxWidth: '90%' }, data: { msg: loginToken.msg } });
        }
        this.loading = false;
      });
    }
  }

  private validaTela(): boolean {
    if (this.form.controls.login.invalid) {
      this.form.markAllAsTouched();
      return false;
    }

    if (this.form.controls.senha.invalid) {
      this.form.markAllAsTouched();
      return false;
    }
    return true;
  }

  protected esqueciSenha() {
    this.router.navigate(['esqueci-senha'], { relativeTo: this.activatedRoute });
  }
}
