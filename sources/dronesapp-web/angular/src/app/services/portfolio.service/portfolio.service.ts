import { Injectable } from '@angular/core';

import { PortfolioModel } from './portfolio.interface'
import {RequestService} from '../request.service/request.service';

interface Portfolios extends Array<PortfolioModel>{}

@Injectable()
export class PortfolioService {
  public items: Portfolios = [];

  constructor(
    private _requestService: RequestService
  ) {
    this.items = [
      {
        id: 0,
        title: 'Title',
        itemURL: './assets/img/project1.jpg',
        type: 'PHOTO'
      },
      {
        id: 1,
        title: 'Title',
        itemURL: './assets/img/project2.jpg',
        type: 'PHOTO'
      },
      {
        id: 2,
        title: 'Title',
        itemURL: './assets/img/project1.jpg',
        type: 'PHOTO'
      },
      {
        id: 3,
        title: 'Title',
        itemURL: './assets/img/project2.jpg',
        type: 'PHOTO'
      }
    ];
  }

  getPortfolios(): any {
    // return this._requestService.fetch('get', '/portfolio')
    //   .subscribe((res) => {
    //     this.items = res;
    //
    //     console.log('portfolios', this.items);
    //
    //     return this.items;
    //   });
    return this.items;
  }

  deletePortfolio(id: number) {
    // return this._requestService.fetch('remove', `/portfolio/${id}`)
    //   .subscribe(
    //     res => {
    //       this.items = this.items.filter(el => el.id !== id);
    //     }
    //   );
    this.items = this.items.filter(el => el.id !== id);
  }

}
