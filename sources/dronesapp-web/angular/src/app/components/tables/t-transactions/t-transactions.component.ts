import {Component, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 't-transactions',
  templateUrl: './t-transactions.component.html',
  styleUrls: ['./t-transactions.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TTransactionsComponent {
  @Input() transactions;

  constructor(
  ) {
  }

  formatDescription(transaction) {
    let out = '';

    if (transaction.type === 'WITHDRAW') {
      out = `WITHDRAWAL: ${transaction.purpose}`
    } else if (transaction.type === 'PAID_OPTION') {
      out = `Project #${transaction.projectId}: option payment`
    } else if (transaction.type === 'PROJECT_PAYMENT') {
      out = `Project #${transaction.projectId}: project payment`
    } else if (transaction.type === 'PROJECT_REJECT') {
      out = `Project #${transaction.projectId}: project reject`
    } else if (transaction.type === 'PAYMENT_RELEASED') {
      out = `Project #${transaction.projectId}: project payment`
    }

    return out;
  }

}
