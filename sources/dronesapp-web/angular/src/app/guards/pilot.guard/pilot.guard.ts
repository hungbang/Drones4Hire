import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';

import {AccountService} from '../../services/account.service/account.service';

@Injectable()
export class PilotGuard implements CanActivate {
  constructor(private _accountService: AccountService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    console.log('-activate pilot guard');

    if (this._accountService.account && this._accountService.isUserPilot()) {
      return true;
    } else {
      return false;
    }
  }
}
