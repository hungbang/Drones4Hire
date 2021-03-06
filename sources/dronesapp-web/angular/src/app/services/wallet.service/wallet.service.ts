import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';

@Injectable()
export class WalletService {
  private allowedTypes = {
    pilot: [
      'PAYMENT_RELEASED',
      'WITHDRAW'
    ],
    client: [
      'PAID_OPTION',
      'PROJECT_PAYMENT',
      'WITHDRAW'
    ]
  };

  constructor(
    private requestService: RequestService
  ) { }

  public getWallet() {
    return this.requestService.fetch('get', '/wallets');
  }

  public getTransactions() {
    return this.requestService.fetch('get', '/wallets/transactions');
  }

  public searchTransactions(data: any) {
    return this.requestService.fetch('post', '/wallets/transactions/search', data);
  }

  get pilotTransactionsTypes() {
    return this.allowedTypes.pilot;
  }

  get clientTransactionsTypes() {
    return this.allowedTypes.client;
  }
}
