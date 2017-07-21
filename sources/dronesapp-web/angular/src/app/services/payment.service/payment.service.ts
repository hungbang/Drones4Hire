import { Injectable } from '@angular/core';

import {RequestService} from '../request.service/request.service';

@Injectable()
export class PaymentService {

  constructor(
    private _requestService: RequestService
  ) {
  }

  releasePayment(projectId) {
    return this._requestService.fetch('post', `/projects/${projectId}/release`);
  }

  getToken() {
    return this._requestService.fetch('get', '/payments/token');
  }

}
