import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

import {AccountService} from '../../services/account.service/account.service';
import {AuthGuard} from '../auth.guard/auth.guard';

@Injectable()
export class ClientGuard implements CanActivate {
  constructor(
    private _accountService: AccountService,
    private authGuard: AuthGuard
  ) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    console.log('-activate client guard');

    return Observable.create(observer => {
      let authed = false;
      this.authGuard.canActivate(_route, state)
        .subscribe(
          res => {
            authed = res;

            if (!authed) {
              observer.next(false);
              observer.complete();
            }

            if (this._accountService.isUserClient()) {
              observer.next(true);
              observer.complete();
            } else {
              observer.next(false);
              observer.complete();
            }
          },
          () => {
            observer.next(false);
            observer.complete();
          }
        );
    });
  }
}
