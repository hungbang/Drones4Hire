import { Component, ViewEncapsulation } from '@angular/core';
import { AppService } from '../../../services/app.service/app.service';
import { AccountService } from '../../../services/account.service/account.service';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import {CommonService} from '../../../services/common.service/common.service';

@Component({
  selector: 's-account',
  templateUrl: './s-account.component.html',
  styleUrls: ['./s-account.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SAccountComponent {

  // todo Make tab with routing
  activeTabName = 'details';

  constructor(
    public _appService: AppService,
    private _authorizationService: AuthorizationService,
    public _accountService: AccountService
  ) {}

  showTab(tabName) {
    this.activeTabName = tabName;
  }
}
