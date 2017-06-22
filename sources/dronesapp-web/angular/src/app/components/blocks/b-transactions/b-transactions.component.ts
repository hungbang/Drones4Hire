import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {AccountService} from "../../../services/account.service/account.service";

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
    private accountService: AccountService
  ) { }

  ngOnInit() {
    this.getTransactions();
  }

  private getTransactions() {
    this.accountService.getTransactions()
      .subscribe(
        res => {
          console.log('transactions: ', res);
          if (res.length) {
            this.transactions = res;
            this.maxPage = Math.ceil(this.transactions.length / this.countPerPage);
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
