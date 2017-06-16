import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, ActivatedRoute} from '@angular/router';

import {BidService} from '../../services/bid.service/bid.service';
import {createObservable} from "../../shared/common/common-methods";

@Injectable()
export class BidsResolve implements Resolve<any> {

  constructor(
    private bidService: BidService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const projectId = Number(route.params['projectId']);

    if (isNaN(projectId)) {
      return createObservable(null);
    }

    return this.bidService.fetchBids(projectId);
  }
}
