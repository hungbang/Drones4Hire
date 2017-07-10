import {Component, ViewEncapsulation, Input} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 't-transactions',
  templateUrl: './t-transactions.component.html',
  styleUrls: ['./t-transactions.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TTransactionsComponent {
  @Input() transactions: any[] = [];

  constructor(
    private accountService: AccountService
  ) {
  }

  formatDescription(transaction) {
    let out = '';

    if (transaction.type === 'WITHDRAW') {
      out = transaction.purpose;
    } else if (transaction.type === 'PAID_OPTION') {
      out = `Project #${transaction.projectId}: paid options`;
    } else if (transaction.type === 'PROJECT_PAYMENT' || transaction.type === 'PAYMENT_RELEASED') {
      out = `Project #${transaction.projectId}: project payment`;
    } else if (transaction.type === 'PROJECT_REJECT') {
      out = `Project #${transaction.projectId}: project reject`;
    } else if (transaction.type === 'SERVICE_FEE') {
      out = `Project #${transaction.projectId}: service fee`;
    }

    return out;
  }

  amountClass(transaction) {
    const isClient = this.accountService.isUserClient();

    switch (transaction.type) {
      case 'WITHDRAW':
        return isClient ? '_green' : '_red';
      case 'PAID_OPTION':
        return isClient ? '_red' : '_green';
      case 'PROJECT_PAYMENT':
        return isClient ? '_red' : '_green';
      case 'PAYMENT_RELEASED':
        return isClient ? '_red' : '_green';
      default:
        return '';
    }
  }

}
