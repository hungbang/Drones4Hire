import { Injectable } from '@angular/core';

import { PortfolioModel } from './portfolio.interface'
import {RequestService} from '../request.service/request.service';

@Injectable()
export class PortfolioService {
  public items: Array<PortfolioModel> = [];

  constructor(
    private _requestService: RequestService
  ) { }

  getPortfolios(id): any {
    return this._requestService.fetch('get', `/portfolio?id=${id}`)
      .subscribe((res) => {
        this.items = res;

        // console.log('portfolios', this.items);

        return this.items;
      });
  }

  deletePortfolio(id: number) {
    return this._requestService.fetch('remove', `/portfolio/${id}`)
      .map(
        res => {
          this.items = this.items.filter(el => el.id !== id);
          return res;
        }
      );
  }

  addPortfolio(portfolio: PortfolioModel) {
    return this._requestService.fetch('post', '/portfolio', portfolio)
      .map(
        res => {
          // console.log('added portfolio:', res);
          this.items.push(res);
          return res;
        }
      );
  }

}
