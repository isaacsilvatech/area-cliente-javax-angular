import { Injectable } from '@angular/core';

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { DialogService } from 'primeng/dynamicdialog';
import { Observable, tap } from 'rxjs';
import { ErroDialogComponent } from '../../shared/components/erro-dialog/erro-dialog.component';

@Injectable({
    providedIn: 'root'
})
export class HttpErrorMessageInterceptor implements HttpInterceptor {

    constructor(private dialog: DialogService) { }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(tap({
            error: async (erro: HttpErrorResponse) => {
                this.dialog.open(ErroDialogComponent, { header: 'Ops! Algo deu errado.', style: { maxWidth: '90%' }, data: { msg: erro.statusText } });
            }
        }));
    }
}