import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { MainComponent } from './main/main.component';
import { ButtonModule } from 'primeng/button';
import { SidebarModule } from 'primeng/sidebar';
import { RouterModule } from '@angular/router';
import { HeaderCategorieComponent } from './header/header-categorie/header-categorie.component';
import { HeaderMenuComponent } from './header/header-menu/header-menu.component';



@NgModule({
  exports: [
    HeaderComponent,
    HeaderCategorieComponent,
    MainComponent,
    HeaderMenuComponent
  ],
  declarations: [
    HeaderComponent,
    MainComponent,
    HeaderCategorieComponent,
    HeaderMenuComponent
  ],
  imports: [
    CommonModule,
    ButtonModule,
    SidebarModule,
    RouterModule
  ]
})
export class TemplateModule { }
