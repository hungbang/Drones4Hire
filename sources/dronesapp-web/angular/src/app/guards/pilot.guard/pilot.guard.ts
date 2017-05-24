import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AccountService} from '../../services/account.service/account.service';
import {AuthGuard} from '../auth.guard/auth.guard';

@Injectable()
export class PilotGuard implements CanActivate {
  constructor(private _accountService: AccountService,
              private _router: Router,
              private _authGuard: AuthGuard) {
  }

  canActivate() {
    console.log('-activate pilot guard');

    return this._authGuard.canActivate()
      .map((auth: boolean) => {
        if (!auth) {
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
