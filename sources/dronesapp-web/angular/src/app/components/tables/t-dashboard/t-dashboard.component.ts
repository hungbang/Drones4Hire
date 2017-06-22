import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import {PaymentService} from "../../../services/payment.service/payment.service";
import * as moment from 'moment';

@Component({
  selector: 't-dashboard',
  templateUrl: './t-dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./t-dashboard.component.styl']
})
export class TDashboardComponent implements OnInit {
  @Input() projects;

  constructor(
    public _accountService: AccountService,
    private _paymentService: PaymentService
  ) { }

  ngOnInit() {
  }

  get isClient() {
    return this._accountService.isUserClient();
  }

  get isPilot() {
    return this._accountService.isUserPilot();
  }

  release(project, isCanRelease, event) {
    event.preventDefault();

    if (isCanRelease) {
      this._paymentService.releasePayment(project.bidId)
        .subscribe(() => {
          project.paymentReleased = Number(moment().format('x'))
        });
    }
  }
}
