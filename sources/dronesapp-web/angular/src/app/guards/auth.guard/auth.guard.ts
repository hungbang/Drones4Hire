import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AccountService } from '../../services/account.service/account.service';
import { AuthorizationService } from '../../services/authorization.service/authorization.service';
import { TokenService } from '../../services/token.service/token.service';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(
    private _authorizationService: AuthorizationService,
    private _accountService: AccountService,
    private _tokenService: TokenService
    ) {
  }

  canActivate() {
    if (localStorage.getItem('accessToken') && localStorage.getItem('refreshToken')) {
      this._tokenService.setAccessToken(localStorage.accessToken);
      this._tokenService.setRefreshToken(localStorage.refreshToken);
      return this._accountService.getUserData()
        .then((res) => {
          this._accountService.currentUser = res.json();
          this._authorizationService.isUserLogin = true;
          return true;
        });
    } else {
      return true;
    }
  }
}
