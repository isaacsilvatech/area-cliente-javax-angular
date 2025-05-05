import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { PT_BR } from './core/lang/pt-br';

@Component({
  selector: 'app-root',
  template: `
  <p-toast />
  <router-outlet></router-outlet>
  `,
})
export class AppComponent implements OnInit {

  constructor(private config: PrimeNGConfig) {
  }

  public ngOnInit(): void {
    this.config.setTranslation(PT_BR);
  }
}
