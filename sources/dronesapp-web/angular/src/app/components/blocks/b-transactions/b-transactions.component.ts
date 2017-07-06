import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {WalletService} from '../../../services/wallet.service/wallet.service';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'b-transactions',
  templateUrl: './b-transactions.component.html',
  styleUrls: ['./b-transactions.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BTransactionsComponent implements OnInit {
  public transactions: any[] = [];
  public currentPage = 1;
  public minPage = 1;
  public maxPage = 1;
  private countPerPage = 20;

  constructor(
    private walletService: WalletService,
    private accountService: AccountService
  ) { }

  ngOnInit() {
    this.getTransactions(this.accountService.account.wallet.id);
  }

  private getTransactions(walletId) {
    const searchData = {
      sortOrder: 'DESC',
      walletId: walletId
    };

    this.walletService.searchTransactions(searchData)
      .subscribe(
        res => {
          // console.log('transactions: ', res);
          if (res.totalResults) {
            this.transactions = res.results;
            this.maxPage = Math.ceil(this.transactions.length / this.countPerPage); // TODO: make real pagination via routes
          }
        },
        err => {
          console.log('get transactions error:', err);
        }
      );
  }

  changePage(page) {
    this.currentPage = page;
  }
}
