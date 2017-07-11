import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';

import {AccountService} from '../../services/account.service/account.service';

@Injectable()
export class ClientGuard implements CanActivate {
  constructor(private _accountService: AccountService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log('-activate client guard');

    if (this._accountService.account && this._accountService.isUserClient()) {
      return true;
    } else {
      return false;
    }
  }
}
