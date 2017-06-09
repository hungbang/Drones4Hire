import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';

@Component({
  selector: 's-portfolio',
  templateUrl: './s-portfolio.component.html',
  styleUrls: ['./s-portfolio.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SPortfolioComponent implements OnInit {

  constructor(
    public portfolioService: PortfolioService
  ) { }

  ngOnInit() {
    if (!this.portfolioService.items) {
      this.portfolioService.getPortfolios();
    }
  }

}
