import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ProximoCampoModule } from '../../shared/directives/proximo-campo/proximo-campo.module';
import { TemplateModule } from "../../shared/template/template.module";
import { LoginCadastroDialogComponent } from './login-cadastro-dialog/login-cadastro-dialog.component';
import { LoginEsqueciSenhaComponent } from './login-esqueci-senha/login-esqueci-senha.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { LoginRecuperarSenhaComponent } from './login-recuperar-senha/login-recuperar-senha.component';
import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { LoginRecuperacaoSenhaEnviadaComponent } from './login-recuperacao-senha-enviada/login-recuperacao-senha-enviada.component';
import { LoginAlterarSenhaComponent } from './login-alterar-senha/login-alterar-senha.component';


@NgModule({
  declarations: [
    LoginComponent,
    LoginFormComponent,
    LoginEsqueciSenhaComponent,
    LoginRecuperarSenhaComponent,
    LoginCadastroDialogComponent,
    LoginRecuperacaoSenhaEnviadaComponent,
    LoginAlterarSenhaComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    TemplateModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
    PasswordModule,
    DialogModule,
    ProximoCampoModule,
    RadioButtonModule
  ]
})
export class LoginModule { }
