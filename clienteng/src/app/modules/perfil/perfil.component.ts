import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { filter } from 'rxjs';
import { TITLE } from '../../../environments/environment';
import { PessoaLogada } from '../../core/model/pessoa-logada.model';
import { PessoaLogadaService } from '../../core/service/pessoa-logada.service';
import { PerfilService } from './perfil.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'cmp-perfil',
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.scss'
})
export class PerfilComponent {

  protected pessoaLogada: PessoaLogada;
  protected form = new FormGroup({
    cpfCnpj: new FormControl<string>(null, [Validators.required]),
    nomeRazaoSocial: new FormControl<string>('', [Validators.required]),
    celular: new FormControl<string>(null, [Validators.required, Validators.minLength(11)]),
    email: new FormControl<string>(null, [Validators.required]),
    colaborador: new FormControl<boolean>(null, [Validators.required]),
    tipoNascimento: new FormControl<number>(null, [Validators.required]),
    nascDia: new FormControl<number>(null, [Validators.required]),
    nascMes: new FormControl<number>(null, [Validators.required]),
    dataNacimento: new FormControl<Date>(null, [Validators.required]),
    receberContatoColaborador: new FormControl<boolean>(null, [Validators.required]),
    receberOfertas: new FormControl<boolean>(null, [Validators.required]),
  });
  protected loading: boolean = false;

  constructor(
    private title: Title,
    private pessoaLogadaService: PessoaLogadaService,
    private service: PerfilService,
    private messageService: MessageService
  ) {
    this.title.setTitle(`Perfil - ${TITLE}`);
    this.pessoaLogadaService.getPessoaLogada().pipe(filter(p => !!p)).subscribe(pessoaLogada => {
      this.pessoaLogada = pessoaLogada;
    });

    this.loading = true;
    this.service.getDados().subscribe(dados => {
      this.form.patchValue(dados);
      this.loading = false;
    })
  }

  protected salvar(): void {
    if (this.validaTela()) {
      this.loading = true;
      this.service.salvar(this.form.getRawValue()).subscribe(dados => {
        this.form.patchValue(dados);
        this.messageService.add({
          severity: 'success', summary: 'Sucesso',
          detail: 'Dados salvos com sucesso!',
        });
        this.loading = false;
      })
    }
  }

  protected validaTela(): boolean {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return false;
    }
    return true;
  }
}
