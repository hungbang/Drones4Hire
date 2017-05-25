import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import 'rxjs/add/operator/map';
import {AccountModel} from './account.interface';
import {TokenService} from '../token.service/token.service';
import {AccountCompanyModel} from './accountCompany.interface';
import {AccountLicenseModel} from './accountLicense.interface';
import {AccountNotificationsModel} from './accountNotifications.interface';
import {AppService} from '../app.service/app.service';
import {CommonService} from '../common.service/common.service';
import {AccountProfileModel} from './accountProfile.interface';

@Injectable()
export class AccountService {
  account: AccountModel = null;
  company: AccountCompanyModel = null;
  license: AccountLicenseModel = null;
  notifications: AccountNotificationsModel = null;
  services: Array<number> = [];
  profile: AccountProfileModel = null;

  constructor(private _requestService: RequestService,
              private _tokenService: TokenService,
              private _appService: AppService,
              private _commonService: CommonService) {
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
        this.account = this._appService.mergeDeep(this.setAccount(), res);

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
      .subscribe((res) => {
        this.license = {...res, verified: true};

        console.log('user license', this.license);

        return this.license;
      });
  }

  setUserLicense(data: AccountLicenseModel) {
    return this._requestService.fetch('put', '/account/license', data);
  }

  setEmailAddress(data: { email: string, password: string }) {
    return this._requestService.fetch('put', '/account/email', data);
  }

  setPassword(data: { confirmPassword: string, password: string }) {
    return this._requestService.fetch('put', '/account/password', data);
  }

  getUserNotifications() {
    return this._requestService.fetch('get', '/account/notifications')
      .subscribe(res => {
        console.log('user notifications', res);
        this.notifications = res;
      })
  }

  setUserNotifications(data: AccountNotificationsModel) {
    return this._requestService.fetch('put', '/account/notifications', data);
  }

  getUserServices() {
    this._requestService.fetch('get', '/account/services')
      .map(res => {
        this.services = res.map((service) => service.id);
        console.log('user services', this.services);
        return res;
      })
      .subscribe(() => {
        this._commonService.getListOfServices(this.services);
      });
  }

  setUserServices(data: Array<number>) {
    return this._requestService.fetch('put', '/account/services', data);
  }

  getAccountProfile() {
    this._requestService.fetch('get', '/account/profile')
      .subscribe(res => {
        this.profile = res;
        console.log('user profile', this.profile);
        return res;
      });
  }

  setAccountProfile(data) {
    return this._requestService.fetch('put', '/account/profile', data);
  }

  isAuthorized() {
    console.log(this._tokenService.accessToken && this._tokenService.refreshToken, '-auth');
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
