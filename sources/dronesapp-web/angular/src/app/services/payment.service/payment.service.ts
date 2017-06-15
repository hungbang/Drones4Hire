import { Injectable } from '@angular/core';
import {RequestService} from "../request.service/request.service";

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

  constructor(
    private _requestService: RequestService
  ) {
    this.amount = 3603.44;
  }

  releasePayment(id) {
    return this._requestService.fetch('post', `/payments/${id}`);
  }

}
