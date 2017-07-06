import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';

@Injectable()
export class WalletService {

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
}
