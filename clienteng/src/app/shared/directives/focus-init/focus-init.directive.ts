import { AfterViewInit, Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[cmpFocusInit]'
})
export class FocusInitDirective implements AfterViewInit {

  constructor(private el: ElementRef) {

  }
  ngAfterViewInit(): void {
    setTimeout(() => {
      this.el.nativeElement
        .querySelector(`
        a[href]:not([disabled]),
        button:not([disabled]),
        textarea:not([disabled]),
        input:not([disabled]),
        select:not([disabled])
        `)?.focus();
    })
  }
}
