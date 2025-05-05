import { inject } from '@angular/core';
import { CanMatchFn } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { PessoaLogadaService } from '../service/pessoa-logada.service';

export const authGuard: CanMatchFn = () => {

  const auth = inject(AuthService);
  const pessoaLogada = inject(PessoaLogadaService);

  if (!auth.isTokenValido()) {
    pessoaLogada.removePessoaLogada();
    auth.removeToken();
    return false;
  }

  return true;
}
