import { Injectable } from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {BidModel} from './bid.interface';

@Injectable()
export class BidService {
  public countPerPage = 10;
  public countOfItemsSelection = [10, 20, 30];

  constructor(
    private requestService: RequestService
  ) {

  }

  fetchBids(projectId: string|number) {
    return this.requestService
      .fetch('get', `/projects/${projectId}/bids`);
  }

  createBid(bid) {
    return this.requestService
      .fetch('post', '/bids', bid);
  }

  editBid(bid) {
    return this.requestService
      .fetch('put', '/bids', bid);
  }

  accept(id: number) {
    return this.requestService
      .fetch('post', `/bids/${id}/accept`);
  }

  reject(id: number) {
    return this.requestService
      .fetch('post', `/bids/${id}/reject`);
  }

  award(id: number) {
    return this.requestService
      .fetch('post', `/bids/${id}/award`);
  }
}
