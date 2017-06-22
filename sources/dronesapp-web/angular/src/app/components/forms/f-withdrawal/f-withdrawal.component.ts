import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';

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

  constructor(
    private accountService: AccountService
  ) { }

  ngOnInit() {
  }

  sendRequest(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid || !this.isCorrectValue || !this.canWithdarawal) {
      return;
    }

    console.log('requested withdrawal amount:', parseFloat(this.amount)); // TODO: connect to API
    form.resetForm();
    this.submitted = false;
  }

  get isCorrectValue() {
    const value = parseFloat(this.amount); // TODO: use replacement on enter instead parsing?
    return isFinite(value) && value > 0 && value <= this.balance;
  }

  get paymentLink() {
    return this.accountService.account.paymentLink ? this.accountService.account.paymentLink : '#';
  }

  get canWithdarawal() {
    return false; // TODO: connect to API when will ready
  }

}
