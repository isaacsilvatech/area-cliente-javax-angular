import { CommonModule, DatePipe, registerLocaleData } from '@angular/common';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { JwtModule } from '@auth0/angular-jwt';
import { provideNgxMask } from 'ngx-mask';
import { DialogService, DynamicDialogModule } from 'primeng/dynamicdialog';
import { ErroDialogModule } from '../shared/components/erro-dialog/erro-dialog.module';
import { HttpAuthInterceptor } from './http/http-auth.interceptor';
import { HttpBodyInterceptor } from './http/http-body.interceptor';
import { HttpDestroyerInterceptor } from './http/http-destroyer.interceptor';
import { HttpErrorMessageInterceptor } from './http/http-error-message.interceptor';
import { ACCESS_TOKEN_STORAGE_KEY } from './service/auth.service';

registerLocaleData(localePt);

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    HttpClientModule,
    DynamicDialogModule,
    ErroDialogModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => localStorage.getItem(ACCESS_TOKEN_STORAGE_KEY),
      }
    }),
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt-BR' },
    { provide: DEFAULT_CURRENCY_CODE, useValue: 'BRL', },
    { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpDestroyerInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorMessageInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpBodyInterceptor, multi: true },
    DatePipe,
    provideNgxMask(),
    DialogService,
  ]
})
export class CoreModule { }
