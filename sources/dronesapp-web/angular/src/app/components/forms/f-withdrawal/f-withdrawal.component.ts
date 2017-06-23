import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import {TransactionService} from '../../../services/transaction.service/transaction.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

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
  @Input() balance: number = 0;
  @Output() showSuccessText: EventEmitter<boolean> = new EventEmitter();

  constructor(
    private accountService: AccountService,
    private transactionService: TransactionService,
    private toastrService: ToastrService
  ) { }

  ngOnInit() {
  }

  sendRequest(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || !this.isCorrectValue || !this.canWithdarawal) {
      return;
    }

    console.log('requested withdrawal amount:', parseFloat(this.amount.replace(',', '.')));
    const withdrawal = {
      amount: parseFloat(this.amount.replace(',', '.')),
      comment: this.description,
      currency: this.accountService.account.wallet.currency
    };

    this.transactionService.sendWithdrawal(withdrawal)
      .subscribe(
        res => {
          console.log('saved withdrawal:', res);
          form.resetForm();
          this.showSuccessText.emit(true);
        },
        err => {
          console.log(err);
          const body = err.json();

          if (err.status === 400) {
            if (body && body.validationErrors) {
              body.validationErrors.forEach(item => {
                this.toastrService.showError(item.field);
              });
            } else {
              this.toastrService.showError('Please check your data');
            }
          }
        }
      );
    this.submitted = false;
  }

  get isCorrectValue() {
    const value = parseFloat(this.amount.replace(',', '.')); // TODO: use replacement on enter instead parsing?
    return isFinite(value) && value > 0 && value <= this.balance;
  }

  get paymentLink() {
    return this.accountService.account.paymentLink ? this.accountService.account.paymentLink : '#';
  }

  get canWithdarawal() {
    return true; // TODO: connect to API when will ready
  }
}
