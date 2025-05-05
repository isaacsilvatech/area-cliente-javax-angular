import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginAlterarSenhaComponent } from './login-alterar-senha/login-alterar-senha.component';
import { LoginEsqueciSenhaComponent } from './login-esqueci-senha/login-esqueci-senha.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginRecuperacaoSenhaEnviadaComponent } from './login-recuperacao-senha-enviada/login-recuperacao-senha-enviada.component';
import { LoginRecuperarSenhaComponent } from './login-recuperar-senha/login-recuperar-senha.component';
import { LoginComponent } from './login.component';

const routes: Routes = [
  {
    path: '',
    component: LoginComponent,
    children: [
      {
        path: '',
        component: LoginFormComponent
      },
      {
        path: 'esqueci-senha',
        component: LoginEsqueciSenhaComponent
      },
      {
        path: 'recuperar-senha',
        component: LoginRecuperarSenhaComponent
      },
      {
        path: 'recuperacao-senha-enviada',
        component: LoginRecuperacaoSenhaEnviadaComponent
      },
      {
        path: 'alterar-senha',
        component: LoginAlterarSenhaComponent
      },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
