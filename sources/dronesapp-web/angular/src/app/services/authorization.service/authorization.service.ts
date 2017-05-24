import { Injectable } from '@angular/core';
import { RequestService } from '../request.service/request.service';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { TokenService } from '../token.service/token.service';
import { AccountService } from '../account.service/account.service';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthorizationService {
  public signUpFormActive = true;
  public isUserLogin = false;
  public tokenType: any;

  constructor(
    private _accountService: AccountService,
    private _requestService: RequestService,
    private _tokenService: TokenService
  ) {}

  saveTokens(res: any) {
    this._tokenService.setAccessToken((res as any).accessToken);
    this._tokenService.setRefreshToken((res as any).refreshToken);
  }

  logout() {
    this.isUserLogin = false;
    this._tokenService.removeTokensFromLocalStorage();
    sessionStorage.removeItem('user');
    this._accountService.account = null;
  }

  signIn(formData) {
    return this._requestService.fetch('post', '/auth/login', formData)
      .map((res) => {
        this.tokenType = res.type;
        this.saveTokens(res);
        this.isUserLogin = true;
        return res;
      });


  }

  signUp(formData) {
    return this._requestService.fetch('post', '/auth/register', formData)
      .map((res) => {
        return res;
      });
  }
}
