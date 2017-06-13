import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {PortfolioService} from "../../services/portfolio.service/portfolio.service";

@Injectable()
export class PortfolioResolve implements Resolve<any> {

  constructor(
    private _portfolioService: PortfolioService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const user_id = route.params['user_id'];

    return this._portfolioService.getPortfolios(user_id);
  }
}
