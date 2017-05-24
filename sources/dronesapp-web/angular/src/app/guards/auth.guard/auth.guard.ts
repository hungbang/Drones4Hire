import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';
import {AuthorizationService} from '../../services/authorization.service/authorization.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private _authorizationService: AuthorizationService,
              private _accountService: AccountService,
              private _router: Router) {
  }

  canActivate(): Observable<boolean> {
    console.log('-activate auth guard');

    return Observable.create(observer => {
      if (this._accountService.isAuthorized()) {
        if (!this._accountService.account) {
          return this._accountService.getUserData()
            .then(() => {
              this._authorizationService.isUserLogin = true;
              observer.next(true);
              observer.complete();
            });
        } else {
          observer.next(true);
        }
      } else {
        //this._router.navigate(['/']);
        observer.next(false);
      }
    })
  }
}
