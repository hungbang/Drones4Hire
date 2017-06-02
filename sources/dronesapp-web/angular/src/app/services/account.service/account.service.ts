import {Injectable} from '@angular/core';
import {RequestService} from '../request.service/request.service';
import 'rxjs/add/operator/map';
import {AccountModel} from './account.interface';
import {TokenService} from '../token.service/token.service';
import {AccountCompanyModel} from './accountCompany.interface';
import {AccountLicenseModel} from './accountLicense.interface';
import {AccountNotificationsModel} from './accountNotifications.interface';
import {CommonService} from '../common.service/common.service';
import {AccountProfileModel} from './accountProfile.interface';
import {NormalizedServiceModel} from '../common.service/services.interface';
import {mergeDeep, extend} from '../../shared/common/common-methods';

@Injectable()
export class AccountService {
  account: AccountModel = null;
  company: AccountCompanyModel = null;
  license: AccountLicenseModel = null;
  notifications: AccountNotificationsModel = null;
  services: NormalizedServiceModel[] = [];
  activeServices: Array<number> = [];
  profile: AccountProfileModel = null;

  constructor(private _requestService: RequestService,
              private _tokenService: TokenService,
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

  getAccountData() {
    return this._requestService.fetch('get', '/account')
      .map((res) => {
        this.account = mergeDeep(this.setAccount(), res);

        console.log(this.account);

        // sessionStorage.setItem('user', JSON.stringify(this.account));
        return this.account;
      })
  }

  setAccountData(data: AccountModel) {
    return this._requestService.fetch('put', '/account', data)
  }

  getAccountCompany() {
    return this._requestService.fetch('get', '/account/company')
      .map((res) => {
        this.company = res;

        console.log(this.company);

        // sessionStorage.setItem('user', JSON.stringify(this.account));
        return this.company;
      });
  }

  setAccountCompany(data: AccountCompanyModel) {
    return this._requestService.fetch('put', '/account/company', data);
  }

  getAccountLicense() {
    return this._requestService.fetch('get', '/account/license')
      .subscribe((res) => {
        this.license = {...res, verified: true};

        console.log('user license', this.license);

        return this.license;
      });
  }

  setAccountLicense(data: AccountLicenseModel) {
    return this._requestService.fetch('put', '/account/license', data);
  }

  setAccountEmail(data: { email: string, password: string }) {
    return this._requestService.fetch('put', '/account/email', data);
  }

  setAccountPassword(data: { confirmPassword: string, password: string }) {
    return this._requestService.fetch('put', '/account/password', data);
  }

  getAccountNotifications() {
    return this._requestService.fetch('get', '/account/notifications')
      .subscribe(res => {
        console.log('user notifications', res);
        this.notifications = res;
      })
  }

  setAccountNotifications(data: AccountNotificationsModel) {
    return this._requestService.fetch('put', '/account/notifications', data);
  }

  getAccountServices() {
    this._requestService.fetch('get', '/account/services')
      .map(res => {
        this.activeServices = res.map((service) => service.id);
        console.log('user services', this.activeServices);
        return res;
      })
      .subscribe(() => {
        this._commonService.getServices()
          .subscribe((services) => this.setServices(services));
      });
  }

  setAccountServices(data: Array<number>) {
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
    console.log(this._tokenService.accessToken, '-auth');
    return this._tokenService.accessToken && this._tokenService.refreshToken;
  }

  private setServices(_services) {
    let services = extend([], _services);

    services.forEach((service) => {
      if (this.activeServices.indexOf(service.id) !== -1) {
        return service.checked = true;
      }
      return service.checked = false;
    });

    this.services = this._commonService.normalizeServices(services);
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

  clearData() {
    this.account = null;
    this.company = null;
    this.license = null;
    this.notifications = null;
    this.services = [];
    this.activeServices = [];
    this.profile = null;
  }
}
