import { Component } from '@angular/core';

@Component({
  selector: 'cmp-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

  protected menuOpen = false;
  protected pedidoOpen = false;
}
