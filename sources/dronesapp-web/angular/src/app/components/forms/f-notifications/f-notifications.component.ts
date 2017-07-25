import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'f-notifications',
  templateUrl: './f-notifications.component.html',
  styleUrls: ['./f-notifications.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FNotificationsComponent implements OnInit{
  public submitted: boolean = false;

  constructor(public accountService: AccountService) {
  }

  ngOnInit() {
    if (this.accountService.notifications) {
      return;
    }
    this.accountService.getAccountNotifications();
  }

  changeSettings() {
    this.accountService.setAccountNotifications(this.accountService.notifications)
      .subscribe(res => {
        console.log(res, '-update notifications');
        this.submitted = false;
      });

    this.submitted = true;
  }
}
