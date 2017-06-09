import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, ActivatedRoute} from '@angular/router';

import {BidService} from '../../services/bid.service/bid.service';

@Injectable()
export class BidsResolve implements Resolve<any> {

  constructor(
    private bidService: BidService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const projectId = route.params['projectId'];

    return this.bidService.fetchBids(projectId);
  }
}
