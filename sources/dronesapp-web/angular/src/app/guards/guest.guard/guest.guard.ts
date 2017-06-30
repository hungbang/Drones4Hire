import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

import {AuthorizationService} from '../../services/authorization.service/authorization.service';

@Injectable()
export class GuestGuard implements CanActivate {
  constructor(private _authorizationService: AuthorizationService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return Observable.create(observer => {
      if (this._authorizationService.isUserLogin) {
        observer.next(null);
        observer.complete();
      } else {
        observer.next(true);
        observer.complete();
      }
    });
  }
}
