import { inject } from '@angular/core';
import { CanMatchFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

export const loggedGuard: CanMatchFn = () => {

  const auth = inject(AuthService);
  const router = inject(Router);

  if (auth.isTokenValido()) {
    router.navigateByUrl("/");
    return false;
  }
  return true;
}

