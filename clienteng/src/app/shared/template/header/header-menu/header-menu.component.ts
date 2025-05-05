import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'cmp-header-menu',
  templateUrl: './header-menu.component.html',
  styleUrl: './header-menu.component.scss'
})
export class HeaderMenuComponent {

  @Input()
  public icon: string = 'pi pi-bars';

  @Output() onClick = new EventEmitter<string>();
}
