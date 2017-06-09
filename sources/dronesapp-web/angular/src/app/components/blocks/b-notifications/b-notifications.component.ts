import {Component, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'b-notifications',
  templateUrl: './b-notifications.component.html',
  styleUrls: ['./b-notifications.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BNotificationsComponent {
  constructor(public accountService: AccountService) {
  }
}
