import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, takeUntil } from "rxjs";
import { HttpDestroyerService } from "./http-destroyer.service";

export const HTTP_PRIORITY = 'priority';
export const HTTP_PRIORITY_NO_DESTROY = 'No-Destroy';
export const HTTP_PRIORITY_NO_DESTROY_PARAMETER = `${HTTP_PRIORITY}=${HTTP_PRIORITY_NO_DESTROY}`;

@Injectable({
    providedIn: 'root'
})
export class HttpDestroyerInterceptor implements HttpInterceptor {

    private destroy$: Observable<void>;

    constructor(private service: HttpDestroyerService) {
        this.destroy$ = this.service.getDestroyer();
    }

    public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        if (request.urlWithParams.includes(HTTP_PRIORITY_NO_DESTROY_PARAMETER)) {
            return next.handle(request);
        }

        return next.handle(request).pipe(takeUntil(this.destroy$));
    }
}