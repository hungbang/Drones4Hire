import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {CommonService} from '../../services/common.service/common.service';

@Injectable()
export class DurationsResolve implements Resolve<any> {

  constructor(public commonService: CommonService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.commonService.getDurations();
  }
}
