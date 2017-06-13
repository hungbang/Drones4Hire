import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {BidService} from "../../services/bid.service/bid.service";
import {ProjectService} from "../../services/project.service/project.service";

@Injectable()
export class ClientProjectsResolve implements Resolve<any> {
  constructor(
    private bidService: BidService,
    private projectService: ProjectService
  ) {

  }

  resolve(route: ActivatedRouteSnapshot) {
    const page = parseInt(route.params['page'], 10);

    const sendObj = {page, pageSize: this.projectService.limitProjectsToShow};

    return this.bidService.fetchBidsInfo(sendObj);
  }
}
