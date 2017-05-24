import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';

@Injectable()
export class ClientGuard implements CanActivate {
  constructor(private _accountService: AccountService,
              private _router: Router) {
  }

  canActivate() {
    console.log('-activate client guard');

    if (this._accountService.account && this._accountService.isUserClient()) {
      return true;
    } else if (this._accountService.isAuthorized()) {
      return this._accountService.getUserData()
        .then(() => {
          if (this._accountService.isUserClient()) {
            return true;
          } else {
            this._router.navigate(['/']);
            return false;
          }
        });
    } else {
      this._router.navigate(['/']);
      return false;
    }
  }
}
