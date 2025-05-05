import { Injectable } from '@angular/core';

import { DatePipe } from '@angular/common';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DATE_FORMAT } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class HttpBodyInterceptor implements HttpInterceptor {

    constructor(
        private datePipe: DatePipe
    ) { }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let object = request.body;
        for (let key in object) {
            if (object[key] instanceof Date) {
                object[key] = this.datePipe.transform(object[key], DATE_FORMAT);
            }
        }
        return next.handle(request.clone({ body: object }));
    }
}