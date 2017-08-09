import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';
import * as moment from 'moment';

import {AccountService} from '../../../services/account.service/account.service';
import {PaymentService} from '../../../services/payment.service/payment.service';
import {ModalConfirmationComponent} from '../../modals/modal-confirmation/modal-confirmation.component';
import {ModalService} from '../../../services/modal.service/modal.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';
import {BidService} from '../../../services/bid.service/bid.service';

@Component({
  selector: 't-dashboard',
  templateUrl: './t-dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./t-dashboard.component.styl']
})
export class TDashboardComponent implements OnInit {
  @Input() projects: any[] = [];

  constructor(
    public _accountService: AccountService,
    private _paymentService: PaymentService,
    private _modalService: ModalService,
    private progressbarService: NgProgressService,
    private toastrService: ToastrService,
    private bidService: BidService
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
      const title = 'Release project';
      const message = 'Are you sure you want to release the payment?';

      this._openConfirm((e) => {
        this._release(e, project);
        }, message, title
      );
    }
  }

  acceptFromPilot(project) {
    if (project.status !== 'PENDING') { return; }

    const title = 'Job confirmation';
    const message = 'Are you sure you want to accept this job? Accepting this job creates a binding contract between you and the Client.';

    this._openConfirm((e) => {
        this._acceptPilot(e, project);
      }, message, title
    );
  }

  rejectFromPilot(project) {
    if (project.status !== 'PENDING') { return; }

    const title = 'Job rejection';
    const message = 'Are you sure you want to reject this job?';

    this._openConfirm((e) => {
        this._rejectPilot(e, project)
      }, message, title
    );
  }

  private _openConfirm(confirm, message, title) {
    this._modalService.push({
      component: ModalConfirmationComponent,
      type: 'ModalConfirmationComponent',
      values: {
        title: title,
        message: message,
        confirm_btn_text: 'Yes',
        cancel_btn_text: 'No',
        confirm
      }
    });
  }

  private _acceptPilot(isAccepted, project) {
    if (!isAccepted) {
      this._modalService.pop();
      return;
    }

    this.progressbarService.start();
    this.bidService.accept(project.bidId)
      .subscribe(
        () => {
          this.progressbarService.done();
          this._modalService.pop();
          project.status = 'IN_PROGRESS';
          this.toastrService.showSuccess('Accepted successfully');
        },
        err => {
          this.progressbarService.done();
          this._modalService.pop();
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try later again.');
          } else if (err.status === 400) {
            const body = err.json();
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          } else {
            this.toastrService.showError('Unable to process accept. Please try later.');
          }
        }
      );
  }

  private _rejectPilot(isAccepted, project) {
    if (!isAccepted) {
      this._modalService.pop();
      return;
    }

    this.progressbarService.start();
    this.bidService.reject(project.bidId)
      .subscribe(
        () => {
          this.progressbarService.done();
          this._modalService.pop();
          project.status = 'NEW';
          delete project.bidId;
          this.toastrService.showSuccess('Rejected successfully');
        },
        err => {
          this.progressbarService.done();
          this._modalService.pop();
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try later again.');
          } else if (err.status === 400) {
            const body = err.json();
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            }
          } else {
            this.toastrService.showError('Unable to process reject. Please try later.');
          }
        }
      );
  }
}
