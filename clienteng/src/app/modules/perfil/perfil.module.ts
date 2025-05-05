import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { NgxMaskDirective, NgxMaskPipe } from 'ngx-mask';
import { InputTextModule } from 'primeng/inputtext';
import { RadioButtonModule } from 'primeng/radiobutton';
import { CalendarModule } from 'primeng/calendar';
import { ControleEnabledModule } from '../../shared/directives/controle-enabled/controle-enabled.module';
import { TemplateModule } from "../../shared/template/template.module";
import { PerfilRoutingModule } from './perfil-routing.module';
import { PerfilComponent } from './perfil.component';
import { InputSwitchModule } from 'primeng/inputswitch';
import { InputNumberModule } from 'primeng/inputnumber';
import { ProximoCampoModule } from '../../shared/directives/proximo-campo/proximo-campo.module';


@NgModule({
  declarations: [
    PerfilComponent
  ],
  imports: [
    CommonModule,
    PerfilRoutingModule,
    TemplateModule,
    InputTextModule,
    NgxMaskPipe,
    NgxMaskDirective,
    ControleEnabledModule,
    ReactiveFormsModule,
    RadioButtonModule,
    CalendarModule,
    InputSwitchModule,
    InputNumberModule,
    ProximoCampoModule
  ]
})
export class PerfilModule { }
