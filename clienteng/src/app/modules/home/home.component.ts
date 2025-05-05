import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { TITLE } from '../../../environments/environment';

@Component({
  selector: 'cmp-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  protected pedidoOpen = false;
  protected menuOpen = false;

  constructor(private title: Title) {
    this.title.setTitle(TITLE);
  }
}
