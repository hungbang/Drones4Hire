import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';
import {AuthorizationService} from '../../services/authorization.service/authorization.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private _authorizationService: AuthorizationService,
              private _accountService: AccountService,
              private _router: Router) {
  }

  canActivate() {
    console.log('-activate auth guard');

    if (this._accountService.isAuthorized()) {
      if (!this._accountService.account) {
        return this._accountService.getUserData()
          .then(() => {
            this._authorizationService.isUserLogin = true;
            return true;
          });
      } else {
        return true;
      }
    } else {
      //this._router.navigate(['/']);
      return false;
    }
  }
}
