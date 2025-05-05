import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DialogService } from 'primeng/dynamicdialog';
import { filter } from 'rxjs';
import { PessoaLogada } from '../../core/model/pessoa-logada.model';
import { AuthService } from '../../core/service/auth.service';
import { PessoaLogadaService } from '../../core/service/pessoa-logada.service';
import { ErroDialogComponent } from '../../shared/components/erro-dialog/erro-dialog.component';
import { LoginService } from '../login/login.service';
import { MessageService } from 'primeng/api';
import { Title } from '@angular/platform-browser';
import { TITLE } from '../../../environments/environment';

@Component({
  selector: 'cmp-altera-senha',
  templateUrl: './altera-senha.component.html',
  styleUrl: './altera-senha.component.scss'
})
export class AlteraSenhaComponent {

  protected form = new FormGroup({
    senhaAtual: new FormControl<string>(null, [Validators.required]),
    senhaNova: new FormControl<string>(null, [Validators.required]),
    senhaNovaRepetida: new FormControl<string>(null, [Validators.required]),
  });

  protected loading: boolean = false;

  protected msg: string = null;
  protected pessoaLogada: PessoaLogada

  constructor(
    private service: LoginService,
    private auth: AuthService,
    private pessoaLogadaService: PessoaLogadaService,
    private router: Router,
    private messageService: MessageService,
    private title: Title
  ) {
     this.title.setTitle(`Alterar senha - ${TITLE}`);
    pessoaLogadaService.getPessoaLogada().pipe(filter(p => !!p)).subscribe(p => {
      this.pessoaLogada = p;
    })
  }

  protected salvar(): void {
    if (this.validaTela()) {
      this.loading = true;
      this.service.alterarSenha(this.pessoaLogada.codPessoa, this.form.getRawValue().senhaAtual, this.form.getRawValue().senhaNova).subscribe((msg) => {
        if (msg === 'ok') {
          this.pessoaLogadaService.removePessoaLogada();
          this.auth.removeToken();
          this.router.navigate(["/login"]);
          this.messageService.add({
            severity: 'success', summary: 'Sucesso',
            detail: 'Senha alterada!',
          });
        } else {
          this.msg = msg
        }
        this.loading = false;
      })
    }
  }

  private validaTela(): boolean {
    if (this.form.invalid || this.form.getRawValue().senhaNova != this.form.getRawValue().senhaNovaRepetida) {
      this.form.markAllAsTouched();
      return false;
    }
    return true;
  }
}
