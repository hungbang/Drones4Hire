import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';

import {AccountService} from '../../services/account.service/account.service';''

@Injectable()
export class PilotLicensedGuard implements CanActivate {
  constructor(private _accountService: AccountService) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean>|boolean {
    console.log('-activate pilot licensed guard');

    if (this._accountService.license && this._accountService.license.verified) {
      return Observable.of(true);
    } else {
      return this._accountService.getAccountLicense()
        .map( res => res.verified);
    }
  }
}
