import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {TransactionService} from '../../../services/transaction.service/transaction.service';

@Component({
  selector: 'b-transactions-search',
  templateUrl: './b-transactions-search.component.html',
  styleUrls: ['./b-transactions-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BTransactionsSearchComponent implements OnInit {
  local: {
    transactions: Array<{}>
    currentPage: number
    sortBy: string
    sortDirection: boolean
  };

  constructor(
    public _transactionService: TransactionService
  ) {
    this.local = {
      transactions: [],
      currentPage: 0,
      sortBy: '',
      sortDirection: false
    };
  }

  setNewPage(page: number) {
    this._transactionService.currentPage = page;
  }

  ngOnInit() {
    this.local.transactions = this._transactionService.getTransactions;
    this.local.sortBy = this._transactionService.defaultSort;
    this.local.sortDirection = this._transactionService.defaultDirection;
  }

  sortBy(prop: string) {
    if (this.local.sortBy === prop) {
      this.local.sortDirection = !this.local.sortDirection;
    }
    this.local.sortBy = prop;
    this._transactionService.sortBy(this.local.sortBy, this.local.sortDirection);
  }

}
