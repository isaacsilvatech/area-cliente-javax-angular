import { Component } from '@angular/core';
import { PessoaLogada } from '../core/model/pessoa-logada.model';
import { Title } from '@angular/platform-browser';
import { PessoaLogadaService } from '../core/service/pessoa-logada.service';
import { AuthService } from '../core/service/auth.service';
import { Router } from '@angular/router';
import { TITLE } from '../../environments/environment';
import { filter } from 'rxjs';

@Component({
  selector: 'cmp-modules',
  templateUrl: './modules.component.html',
  styleUrl: './modules.component.scss'
})
export class ModulesComponent {

  protected pessoaLogada: PessoaLogada;

  protected loading: boolean = false;
  protected pedidoOpen: boolean = false;
  protected menuOpen: boolean = false;

  constructor(
    private title: Title,
    private pessoaLogadaService: PessoaLogadaService,
    private auth: AuthService,
    private router: Router
  ) {
    this.title.setTitle(TITLE);
    this.pessoaLogadaService.getPessoaLogada().pipe(filter(p => !!p)).subscribe(pessoaLogada => {
      this.pessoaLogada = pessoaLogada;
    });
  }

  protected logout(): void {
    this.auth.removeToken();
    this.pessoaLogadaService.removePessoaLogada();
    this.router.navigateByUrl('/login');
  }
}
