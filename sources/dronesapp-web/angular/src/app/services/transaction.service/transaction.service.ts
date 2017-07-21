import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';
import {WithdrawalModel} from "./withdrawal.interface";

@Injectable()
export class TransactionService {

  constructor(
    private requestService: RequestService
  ) {
  }

  getClientToDroneTransaction(transactions) {
    return transactions.filter((transaction) => transaction.type === 'PROJECT_PAYMENT');
  }

  getDroneToPilotTransaction(transactions) {
    return transactions.filter((transaction) => transaction.type === 'PAYMENT_RELEASED');
  }

  getLastTransaction(transactions) {
    return transactions.reduce(
      (last, transaction) => last
        ? (transaction.createdAt > last.createdAt ? transaction : last)
        : transaction,
      null
    );
  }

  public sendWithdrawal(withdrawal: WithdrawalModel) {
    return this.requestService.fetch('post', '/withdraws', withdrawal);
  }
}
