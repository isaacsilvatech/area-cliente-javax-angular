import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TITLE } from '../../../environments/environment';

@Component({
  selector: 'cmp-resumo',
  templateUrl: './resumo.component.html',
  styleUrl: './resumo.component.scss'
})
export class ResumoComponent {

  constructor(private title: Title) {
    this.title.setTitle(`Home - ${TITLE}`);
  }
}
