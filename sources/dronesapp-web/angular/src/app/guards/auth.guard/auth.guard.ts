import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';
import {AuthorizationService} from '../../services/authorization.service/authorization.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private _authorizationService: AuthorizationService,
              private _accountService: AccountService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    console.log('-activate auth guard');

    return Observable.create(observer => {
      if (this._accountService.isAuthorized()) {
        if (!this._accountService.account) {

          this._accountService.getAccountData().subscribe(() => {
            this._authorizationService.isUserLogin = true;

            if (this._accountService.isUserPilot()) {
              return this._accountService.getAccountLicense().subscribe(() => {
                observer.next(true);
                observer.complete();
              });
            }

            observer.next(true);
            observer.complete();
          });
        } else {
          observer.next(true);
        }
      } else {
        if (state.url !== '/') {
          observer.next(false);
        } else {
          observer.next(true);
        }
      }
    });
  }
}
