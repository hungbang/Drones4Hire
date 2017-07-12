import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

import {AccountService} from '../../services/account.service/account.service';
import {AuthorizationService} from '../../services/authorization.service/authorization.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private router: Router
  ) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    console.log('-activate auth guard');

    return Observable.create(observer => {
      if (this._authorizationService.isUserLogin) {
        if (!this._accountService.account) {
          this._accountService.getAccountData()
            .subscribe(
              () => {
                observer.next(true);
                observer.complete();
              },
              () => {
                observer.next(false);
                observer.complete();
              }
            )
        } else {
          observer.next(true);
          observer.complete();
        }
      } else {
        if (state.url !== '/') {
          this.router.navigate(['/login']);
          observer.next(false);
          observer.complete();
        } else {
          observer.next(true);
          observer.complete();
        }
      }
    });
  }
}
