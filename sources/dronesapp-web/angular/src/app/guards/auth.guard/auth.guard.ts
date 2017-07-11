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

    if (this._authorizationService.isUserLogin) {
      if (!this._accountService.account) {
        return this._accountService.getAccountData()
          .map(
            () => {
              if (this._accountService.isUserPilot()) {
                this._accountService.getAccountLicense()
                  .subscribe();
              }
              return true;
            }
          )
          .catch(() => Observable.of(false));
      } else {
        return Observable.of(true);
      }
    } else {
      if (state.url !== '/') {
        this.router.navigate(['/login']);
        return Observable.of(false);
      } else {
        return Observable.of(true);
      }
    }
  }
}
