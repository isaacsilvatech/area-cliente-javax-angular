import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'cmp-header-categorie',
  templateUrl: './header-categorie.component.html',
  styleUrl: './header-categorie.component.scss'
})
export class HeaderCategorieComponent {

  @Input() icon: string = '';
  @Input() text: string = '';
  @Input() textBold: string = '';
  @Output() onClick = new EventEmitter<string>();
}
