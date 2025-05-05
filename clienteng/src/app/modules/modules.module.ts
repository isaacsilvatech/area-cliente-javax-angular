import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { SidebarModule } from 'primeng/sidebar';
import { CardModule } from '../shared/components/card/card.module';
import { TemplateModule } from '../shared/template/template.module';
import { ModulesRoutingModule } from './modules-routing.module';
import { ModulesComponent } from './modules.component';



@NgModule({
  declarations: [
    ModulesComponent
  ],
  imports: [
    CommonModule,
    ModulesRoutingModule,
    TemplateModule,
    CardModule,
    ButtonModule,
    SidebarModule,
    RouterModule
  ]
})
export class ModulesModule { }
