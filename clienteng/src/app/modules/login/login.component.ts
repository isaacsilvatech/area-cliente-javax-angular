import { Component } from '@angular/core';

@Component({
  selector: 'cmp-login',
  template: `
    <cmp-header></cmp-header>
    <cmp-main>
        <router-outlet></router-outlet>
    </cmp-main>
  `,
})
export class LoginComponent {


}
