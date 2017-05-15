import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {TransactionService} from "../../../services/transaction.service/transaction.service";

@Component({
  selector: 'l-transactions-search',
  templateUrl: './l-transactions-search.component.html',
  styleUrls: ['./l-transactions-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LTransactionsSearchComponent implements OnInit {
  @Input() transactions: Array<{}>;
  @Input() orderBy: string;
  @Input() orderDirection: boolean;

  constructor(public _transactionService: TransactionService) {
    this.transactions = [];
    this.orderBy = 'date';
    this.orderDirection = false;
  }

  ngOnInit() {
  }
}
