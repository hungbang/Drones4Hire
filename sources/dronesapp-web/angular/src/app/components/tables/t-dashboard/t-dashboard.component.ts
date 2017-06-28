import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import {PaymentService} from "../../../services/payment.service/payment.service";
import * as moment from 'moment';
import {ModalConfirmationComponent} from "../../modals/modal-confirmation/modal-confirmation.component";
import {ModalService} from "../../../services/modal.service/modal.service";

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
    private _paymentService: PaymentService,
    private _modalService: ModalService,
  ) { }

  ngOnInit() {
  }

  get isClient() {
    return this._accountService.isUserClient();
  }

  get isPilot() {
    return this._accountService.isUserPilot();
  }

  private _release(isAccepted, project) {
    if (!isAccepted) {
      this._modalService.pop();
      return;
    }

    this._paymentService.releasePayment(project.id)
      .subscribe(() => {
        this._modalService.pop();
        project.paymentReleased = Number(moment().format('x'));
        project.status = 'COMPLETED';
      });
  }

  release(project, isCanRelease, event) {
    event.preventDefault();

    if (isCanRelease) {
      this._modalService.push({
        component: ModalConfirmationComponent,
        type: 'ModalConfirmationComponent',
        values: {
          title: '',
          message: 'Do you really want to release payments?',
          confirm_btn_text: 'Yes',
          cancel_btn_text: 'No',
          confirm: (e) => this._release(e, project)
        }
      });
    }
  }
}
