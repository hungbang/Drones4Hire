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
    return this.requestService.fetch('get', '/wallets/transactions')
  }
}
