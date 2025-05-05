import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AlteraSenhaRoutingModule } from './altera-senha-routing.module';
import { AlteraSenhaComponent } from './altera-senha.component';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { ProximoCampoModule } from '../../shared/directives/proximo-campo/proximo-campo.module';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AlteraSenhaComponent
  ],
  imports: [
    CommonModule,
    AlteraSenhaRoutingModule,
    PasswordModule,
    ButtonModule,
    ProximoCampoModule,
    ReactiveFormsModule
  ]
})
export class AlteraSenhaModule { }
