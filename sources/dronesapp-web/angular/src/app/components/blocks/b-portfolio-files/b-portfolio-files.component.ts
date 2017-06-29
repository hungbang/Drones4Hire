import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {NgProgressService} from 'ngx-progressbar';

import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';
import {AccountService} from '../../../services/account.service/account.service';

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
    private accountService: AccountService,
    private progressbarService: NgProgressService
  ) { }

  ngOnInit() {
    if (!this.portfolioService.items.length) {
      this.portfolioService.getPortfolios(this.accountService.account.id);
    }
  }

  deletePortfolio(id: number) {
    this.progressbarService.start();
    this.portfolioService.deletePortfolio(id)
      .subscribe(
        () => {
          this.progressbarService.done();
        },
        err => {
          console.log('delete portfolio error:', err);
          this.progressbarService.done();
        }
      );
  }
}
