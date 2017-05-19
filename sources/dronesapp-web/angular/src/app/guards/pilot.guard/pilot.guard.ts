import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AccountService } from '../../services/account.service/account.service';
import { AuthorizationService } from '../../services/authorization.service/authorization.service';
import { TokenService } from '../../services/token.service/token.service';

@Injectable()
export class PilotGuard implements CanActivate {
  constructor(
    private _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _tokenService: TokenService,
    private _router: Router
    ) {
  }

  canActivate() {
    if (this._accountService.currentUser && this._accountService.isUserPilot()) {
      return true;
    } else if (this._tokenService.accessToken && this._tokenService.refreshToken) {
      return this._accountService.getUserData()
        .then((res) => {
          this._accountService.currentUser = res.json();
          if (this._accountService.isUserPilot()) {
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
