import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import 'rxjs/add/operator/map';
import {AccountModel} from './account.interface';
import {TokenService} from '../token.service/token.service';
import {AccountCompanyModel} from './accountCompany.interface';

@Injectable()
export class AccountService {
  account: AccountModel = null;
  company: AccountCompanyModel = null;

  constructor(private _requestService: RequestService,
              private _tokenService: TokenService) {
  }

  isUserClient() {
    // console.log('-check is client');

    if (!this.account) {
      return false;
    }
    return this.account.groups.map((group) => group.role).pop() === 'ROLE_CLIENT';
  }

  isUserPilot() {
    // console.log('-check is pilot');
    if (!this.account) {
      return false;
    }
    return this.account.groups.map((group) => group.role).pop() === 'ROLE_PILOT';
  }

  getUserData() {
    return this._requestService.fetch('get', '/account')
      .map((res) => {
        this.account = res;

        console.log(this.account);

        // sessionStorage.setItem('user', JSON.stringify(this.account));
        return this.account;
      })
  }

  setUserData(data: AccountModel) {
    return this._requestService.fetch('put', '/account', data)
  }

  getUserCompany() {
    return this._requestService.fetch('get', '/account/company')
      .map((res) => {
        this.company = res;

        console.log(this.company);

        // sessionStorage.setItem('user', JSON.stringify(this.account));
        return this.company;
      });
  }

  setUserCompany(data: AccountCompanyModel) {
    return this._requestService.fetch('put', '/account/company', data);
  }

  setEmailAdress(data: {email: string, password: string}) {
    return this._requestService.fetch('put', '/account/email', data);
  }

  isAuthorized() {
    return this._tokenService.accessToken && this._tokenService.refreshToken;
  }
}
