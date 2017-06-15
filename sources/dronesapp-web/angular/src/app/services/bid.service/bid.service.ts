import { Injectable } from '@angular/core';
import {RequestService} from '../request.service/request.service';
import {BidModel} from './bid.interface';
import {getFromObjectToObject} from "../../shared/common/common-methods";

@Injectable()
export class BidService {
  public countPerPage = 10;
  public countOfItemsSelection = [10, 20, 30];

  constructor(
    private requestService: RequestService
  ) {

  }

  formatBidsToPreview(bids) {
    return bids.map((bid) => {
      const newBid: any = getFromObjectToObject(bid, 'account:firstName', 'account:lastName', 'comment', 'amount', 'id', 'createdAt', 'account:photoURL')

      newBid.isConfirmationValid = true;
      newBid.oldBid = bid;

      return newBid;
    });
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
