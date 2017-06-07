import {Component, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 's-account',
  templateUrl: './s-account.component.html',
  styleUrls: ['./s-account.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SAccountComponent {
  tabs: Array<Object> = null;

  constructor(private _accountService: AccountService) {
    this.tabs = [
      {
        link: 'details',
        text: 'Account Details',
        visibility: true
      },
      {
        link: 'preferences',
        text: 'Account Preferences',
        visibility: this._accountService.isUserPilot()
      },
      {
        link: 'security',
        text: 'Password reset',
        visibility: true
      },
      {
        link: 'notifications',
        text: 'Notifications',
        visibility: false // TODO: temporary hidden
      },
    ]
  }
}
