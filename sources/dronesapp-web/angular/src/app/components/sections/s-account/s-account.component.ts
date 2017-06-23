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
        visibility: true,
        icon: 'user'
      },
      {
        link: 'preferences',
        text: 'Account Preferences',
        visibility: this.isUserPilot,
        icon: 'setting'
      },
      {
        link: 'security',
        text: 'Change password',
        visibility: true,
        icon: 'locked'
      },
      {
        link: 'notifications',
        text: 'Notifications',
        visibility: false, // TODO: temporary hidden
        icon: 'portfolio'
      },
      {
        link: 'portfolio',
        text: 'Portfolio',
        visibility: this.isUserPilot,
        icon: 'profile'
      },
    ]
  }

  get isUserPilot() {
    return this._accountService.isUserPilot();
  }

  get userId() {
    return this._accountService.account.id;
  }

}
