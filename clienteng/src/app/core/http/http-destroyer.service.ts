import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpDestroyerService {

  private detroyerSubject = new Subject<void>();

  constructor() { }

  public getDestroyer(): Observable<void> {
    return this.detroyerSubject.asObservable();
  }

  public destroy(): void {
    this.detroyerSubject.next();
  }
}
