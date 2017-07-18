import { Injectable } from '@angular/core';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {tokenNotExpired} from 'angular2-jwt';

import { RequestService } from '../request.service/request.service';
import { TokenService } from '../token.service/token.service';
import { AccountService } from '../account.service/account.service';

@Injectable()
export class AuthorizationService {
  public signUpFormActive = true;
  public tokenType: any;

  constructor(
    private _accountService: AccountService,
    private _requestService: RequestService,
    private _tokenService: TokenService,
  ) {}

  saveTokens(res: any) {
    this._tokenService.setAccessToken((res as any).accessToken);
    this._tokenService.setRefreshToken((res as any).refreshToken);
  }

  get isUserLogin() {
    return this._tokenService.refreshToken && tokenNotExpired('refreshToken');
  }

  logout() {
    this._tokenService.removeTokens();
    this.clearData();
  }

  signIn(formData) {
    return this._requestService.fetch('post', '/auth/login', formData)
      .map((res) => {
        this.tokenType = res.type;
        this.saveTokens(res);
        return res;
      });
  }

  signUp(formData) {
    return this._requestService.fetch('post', '/auth/register', formData)
      .map((res) => {
        return res;
      });
  }

  private clearData() {
    this._accountService.clearData();
  }

  public verifyEmail(token) {
    return this._requestService.fetch('get', `/auth/register/confirm?token=${token}`);
  }

  public getImportedUserData(token) {
    return this._requestService.fetch('post', `/auth/register/info?token=${token}`, {});
  }
}
