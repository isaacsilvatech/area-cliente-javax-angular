import { Injectable } from '@angular/core';

import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({
    providedIn: 'root'
})
export class HttpAuthInterceptor implements HttpInterceptor {

    constructor(private auth: AuthService, private router: Router) {
    }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let token = this.auth.getToken();
        if (token) {
            request = request.clone({
                setHeaders: { Authorization: `Bearer ${token}` },
            });
        }
        return next.handle(request).pipe(tap({
            error: (e: HttpErrorResponse) => {
                if (e.status == 401) {
                    this.auth.removeToken();
                    this.router.navigateByUrl("/login");
                }
            }
        }));
    }
}