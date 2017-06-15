import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';
import {AccountService} from "../../../services/account.service/account.service";

@Component({
  selector: 'b-portfolio-files',
  templateUrl: './b-portfolio-files.component.html',
  styleUrls: ['./b-portfolio-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BPortfolioFilesComponent implements OnInit {
  public deleteFunc = (e) => { this.deletePortfolio(e) };

  constructor(
    public portfolioService: PortfolioService,
    private accountService: AccountService
  ) { }

  ngOnInit() {
    if (!this.portfolioService.items.length) {
      this.portfolioService.getPortfolios(this.accountService.account.id);
    }
  }

  deletePortfolio(id: number) {
    this.portfolioService.deletePortfolio(id);
  }

}
