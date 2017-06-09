import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {BidService} from '../../services/bid.service/bid.service';

@Injectable()
export class MyProjectsResolve implements Resolve<any> {
  constructor(private bidService: BidService) {}

  resolve(route: ActivatedRouteSnapshot) {
    // todo add filter to request
    const status = route.data['status'];
    const page = route.params['page'];
    const pageSize = this.bidService.countPerPage;

    return this.bidService.fetchBidsInfo({ page: 1, pageSize });
  }
}
