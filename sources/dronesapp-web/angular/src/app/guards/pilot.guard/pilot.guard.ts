import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';
import {AuthGuard} from '../auth.guard/auth.guard';

@Injectable()
export class PilotGuard implements CanActivate {
  constructor(private _accountService: AccountService,
              private _router: Router,
              private _authGuard: AuthGuard) {
  }

  canActivate(_route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log('-activate pilot guard');

    return this._authGuard.canActivate(_route, state)
      .map((auth: boolean) => {
        if (!auth) {
          this._router.navigate(['/login']);
          return false
        }

        if (this._accountService.isUserPilot()) {
          return true;
        } else {
          this._router.navigate(['/']);
          return false;
        }
      });
  }
}
