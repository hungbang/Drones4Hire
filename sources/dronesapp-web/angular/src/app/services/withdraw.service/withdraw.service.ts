import { Injectable } from '@angular/core';

@Injectable()
export class WithdrawService {
  // Will get from server
  balance: number;
  // Will send to server
  withdraw: {
    method: string
    paypal: {
      email: string
    }
    fees: number
    bank: {}
    amount: number
    description: string
  };

  constructor() { }

}
