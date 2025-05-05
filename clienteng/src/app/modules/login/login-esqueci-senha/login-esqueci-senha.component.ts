import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { TITLE } from '../../../../environments/environment';
import { LoginCadastroDialogComponent } from '../login-cadastro-dialog/login-cadastro-dialog.component';
import { LoginService } from '../login.service';

@Component({
  selector: 'cmp-login-esqueci-senha',
  templateUrl: './login-esqueci-senha.component.html',
  styleUrl: './login-esqueci-senha.component.scss'
})
export class LoginEsqueciSenhaComponent {

  protected cpf = new FormControl<string>(null, [Validators.required]);
  protected loading: boolean = false;

  constructor(
    private service: LoginService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private title: Title,
    private dialog: DialogService) {
    this.title.setTitle(`Esqueci minha senha - ${TITLE}`);
  }

  protected continuar(): void {
    if (this.cpf.invalid) {
      this.cpf.markAsTouched();
    } else {
      this.loading = true;
      this.service.isCpfValido(this.cpf.getRawValue()).subscribe(valido => {
        if (valido) {
          this.router.navigate(['../recuperar-senha'], { relativeTo: this.activatedRoute, state: { cpf: this.cpf.getRawValue() } });
        } else {
          this.dialog.open(LoginCadastroDialogComponent, {
            header: 'Ops!',
            closable: true,
            style: { width: '100%', maxWidth: '450px', margin: '1rem' }
          });
        }
        this.loading = false;
      });
    }
  }
}
