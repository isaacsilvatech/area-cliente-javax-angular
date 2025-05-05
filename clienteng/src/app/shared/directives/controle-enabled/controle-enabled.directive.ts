import { Directive, Input, OnChanges, Optional, SimpleChanges } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[cmpControleEnabled]'
})
export class ControleEnabledDirective implements OnChanges {

  @Input() cmpControleEnabled: boolean;

  constructor(@Optional() private ngControl: NgControl) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes["cmpControleEnabled"]) {
      if(!this.ngControl) {
        console.warn("Utilize o cmpControleEnabled junto com o formControlName!");
        return;
      } 

      const action = this.cmpControleEnabled ? 'enable' : 'disable'
      this.ngControl.control[action]();
    }
  }

}
