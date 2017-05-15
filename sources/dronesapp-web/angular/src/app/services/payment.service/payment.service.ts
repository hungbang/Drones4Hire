import { Injectable } from '@angular/core';

@Injectable()
export class PaymentService {
  amount: number; // Will be calculated and not editable in form
  payment: {
    amount: number
    firstName: string
    lastName: string
    postCode: number
    card: {
      type: string
      number: string
      expMonth: number
      expYear: number
    }
  };

  constructor() {
    this.amount = 3603.44;
  }

}
