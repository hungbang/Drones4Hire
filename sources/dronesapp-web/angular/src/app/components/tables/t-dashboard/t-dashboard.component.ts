import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { PilotsService } from '../../../services/pilots.service/pilots.service';
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

  dashboard: Object;
  constructor(
    public _accountService: AccountService,
    public _pilotsService: PilotsService,
    private _paymentService: PaymentService
  ) { }

  ngOnInit() {
    this.dashboard = this._pilotsService.selectedPilot.dashboard;
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
