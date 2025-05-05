import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { Subscription } from 'rxjs';
import { TITLE } from '../../../../environments/environment';
import { AuthService } from '../../../core/service/auth.service';
import { ErroDialogComponent } from '../../../shared/components/erro-dialog/erro-dialog.component';
import { LoginService } from '../login.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'cmp-login-alterar-senha',
  templateUrl: './login-alterar-senha.component.html',
  styleUrl: './login-alterar-senha.component.scss'
})
export class LoginAlterarSenhaComponent implements OnInit, OnDestroy {

  private sub: Subscription;
  private token: string;
  protected loading: boolean = false;
  protected msg: string;

  protected form = new FormGroup({
    senha: new FormControl<string>(null, [Validators.required]),
    confirmarSenha: new FormControl<string>(null, [Validators.required])
  });

  constructor(
    private service: LoginService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService,
    private title: Title,
    private messageService: MessageService
  ) {
    this.title.setTitle(`Alterar Senha - ${TITLE}`);
    this.sub = this.activatedRoute.queryParams.subscribe(params => {
      this.token = params['t'];
      if (!this.authService.isValido(this.token)) {
        this.router.navigate(['/login']);
      }
    });
  }

  public ngOnInit(): void {
  }

  protected alterar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
    } else {
      this.loading = true;
      const codPessoa = this.authService.getCodPessoa(this.token);
      this.service.alterarSenhaEsquecida(this.token, codPessoa, this.form.getRawValue().senha).subscribe(msg => {
        if (msg == "ok") {
          this.router.navigate(['/login']);
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Senha alterada com sucesso!' });
        } else {
          this.msg = msg
        }
        this.loading = false;
      });
    }
  }

  public ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
