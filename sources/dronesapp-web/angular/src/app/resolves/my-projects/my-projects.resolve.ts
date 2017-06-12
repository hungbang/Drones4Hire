import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {BidService} from '../../services/bid.service/bid.service';

@Injectable()
export class MyProjectsResolve implements Resolve<any> {
  constructor(private bidService: BidService) {}

  resolve(route: ActivatedRouteSnapshot) {
    const status = route.data['status'];
    const page = parseInt(route.params['page'], 10);

    const title = route.queryParams['title'];
    const countOfItemsPerPage = route.queryParams['count'] || 10;

    if (countOfItemsPerPage) {
      this.bidService.countPerPage = parseInt(countOfItemsPerPage, 10);
    }

    const pageSize = this.bidService.countPerPage;

    return this.bidService.fetchBidsInfo({ page, pageSize, status, title });
  }
}
