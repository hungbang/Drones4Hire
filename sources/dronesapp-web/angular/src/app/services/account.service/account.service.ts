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

  constructor(
    private _requestService: RequestService,
    private _commonService: CommonService
  ) {
  }

  isUserClient() {
    if (!this.account) {
      return false;
    }

    return !!this.account.groups.find((group) => group.role === 'ROLE_CLIENT');
  }

  isUserPilot() {
    return this.isPilot(this.account);
  }

  isPilot(account) {
    if (!account) {
      return false;
    }

    return !!account.groups.find((group) => group.role === 'ROLE_PILOT');
  }

  getAccountData() {
    return this._requestService.fetch('get', '/account')
      .map((res) => {
        this.account = mergeDeep(this.setAccount(), res);

        return this.account;
      });
  }

  setAccountData(data: AccountModel) {
    return this._requestService.fetch('put', '/account', data);
  }

  getAccountCompany() {
    return this._requestService.fetch('get', '/account/company')
      .map((res) => {
        this.company = res;

        return this.company;
      });
  }

  setAccountCompany(data: AccountCompanyModel) {
    return this._requestService.fetch('put', '/account/company', data);
  }

  getAccountLicense() {
    return this._requestService.fetch('get', '/account/license')
      .map((res) => {
        this.license = res;

        return this.license;
      });
  }

  setAccountLicense(data: AccountLicenseModel) {
    return this._requestService.fetch('put', '/account/license', data);
  }

  setAccountEmail(data: { email: string, password: string }) {
    return this._requestService.fetch('put', '/account/email', data);
  }

  setAccountPassword(data: { currentPassword: string, newPassword: string, confirmPassword: string }) {
    return this._requestService.fetch('put', '/account/password/change', data);
  }

  getAccountNotifications() {
    return this._requestService.fetch('get', '/account/notifications')
      .subscribe(res => {
        this.notifications = res;
      });
  }

  setAccountNotifications(data: AccountNotificationsModel) {
    return this._requestService.fetch('put', '/account/notifications', data);
  }

  getAccountServices() {
    return this._requestService.fetch('get', '/account/services')
      .map(res => {
        this.activeServices = res.map((service) => service.id);

        return this.activeServices;
      })
  }

  setAccountServices(data: Array<number>) {
    return this._requestService.fetch('put', '/account/services', data);
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

  public forgotPassword(email) {
    return this._requestService.fetch('get', `/auth/password/forgot?email=${email}`);
  }

  public resetPassword(token, data) {
    return this._requestService.fetch('post', `/auth/password/reset?token=${token}`, data)
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
