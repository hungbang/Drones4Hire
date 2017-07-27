import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {TransactionService} from '../../../services/transaction.service/transaction.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';
import {WalletModel} from '../../../services/wallet.service/wallet.interface';

@Component({
  selector: 'f-withdrawal',
  templateUrl: './f-withdrawal.component.html',
  styleUrls: ['./f-withdrawal.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FWithdrawalComponent implements OnInit {
  amount: string = '';
  description: string = '';
  submitted: boolean = false;
  @Input() wallet: WalletModel;
  @Output() showSuccessText: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private transactionService: TransactionService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) { }

  ngOnInit() {
  }

  sendRequest(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || !this.canWithdarawal || !this.isCorrectValue || this.isLimitedValue) {
      return;
    }

    console.log('requested withdrawal amount:', parseFloat(this.amount.replace(',', '.')));
    const withdrawal = {
      amount: parseFloat(this.amount.replace(',', '.')),
      comment: this.description,
      currency: this.wallet.currency
    };

    this.progressbarService.start();
    this.transactionService.sendWithdrawal(withdrawal)
      .subscribe(
        res => {
          this.progressbarService.done();
          console.log('saved withdrawal:', res);
          form.resetForm();
          this.showSuccessText.emit(true);
        },
        err => {
          this.progressbarService.done();
          console.log(err);

          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 400) {
            const body = err.json();

            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      );
    this.submitted = false;
  }

  get isCorrectValue() {
    const value = parseFloat(this.amount.replace(',', '.')); // TODO: use replacement on enter instead parsing?
    return isFinite(value) && value > 0 && value <= this.wallet.balance;
  }

  get isLimitedValue() {
    const value = parseFloat(this.amount.replace(',', '.'));

    return this.isCorrectValue && value >= 20;
  }

  get paymentLink() {
    return this.wallet.payoneerRegistrationLink ? this.wallet.payoneerRegistrationLink : '#';
  }

  get canWithdarawal() {
    return this.wallet.withdrawEnabled;
  }
}
