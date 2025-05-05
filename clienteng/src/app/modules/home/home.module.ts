import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { SidebarModule } from 'primeng/sidebar';
import { CardModule } from '../../shared/components/card/card.module';
import { TemplateModule } from '../../shared/template/template.module';
import { HomeRoutingModule } from './home-routing.module';
import { HomeComponent } from './home.component';



@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,
    TemplateModule,
    CardModule,
    ButtonModule,
    SidebarModule,
    RouterModule
  ]
})
export class HomeModule { }
