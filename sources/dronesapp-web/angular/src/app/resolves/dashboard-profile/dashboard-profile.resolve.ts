import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {AccountService} from '../../services/account.service/account.service';


@Injectable()
export class DashboardProfileResolve implements Resolve<any> {

  constructor(
    private accountService: AccountService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.accountService.account;
  }
}
