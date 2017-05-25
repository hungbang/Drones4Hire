import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import 'rxjs/add/operator/map';
import {AccountModel} from './account.interface';
import {TokenService} from '../token.service/token.service';
import {AccountCompanyModel} from './accountCompany.interface';
import {AccountLicenseModel} from './accountLicense.interface';

@Injectable()
export class AccountService {
  account: AccountModel = null;
  company: AccountCompanyModel = null;
  license: AccountLicenseModel = null;

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
        this.account = {...this.setAccount(), ...res};

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

  getUserLicense() {
    return this._requestService.fetch('get', '/account/license')
      .map((res) => {
        this.license = res;

        console.log(this.license);

        return this.license;
      });
  }

  setUserLicense(data: AccountLicenseModel) {
    return this._requestService.fetch('put', '/account/license', data);
  }

  setEmailAdress(data: {email: string, password: string}) {
    return this._requestService.fetch('put', '/account/email', data);
  }

  isAuthorized() {
    return this._tokenService.accessToken && this._tokenService.refreshToken;
  }

  private setAccount(): AccountModel {
    return {
      email: '',
      firstName: '',
      groups: [
        {
          id: null,
          name: '',
          role: '',
        }
      ],
      id: null,
      introduction: '',
      lastName: '',
      location: {
        address: '',
        city: '',
        coordinates: {
          latitude: null,
          longitude: null
        },
        country: {
          id: null,
          name: ''
        },
        id: null,
        postcode: null,
        state: {
          code: '',
          id: null,
          name: ''
        }
      },
      photoURL: '',
      summary: '',
      username: ''
    };
  }
}
