import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {TransactionService} from '../../../services/transaction.service/transaction.service';

@Component({
  selector: 'f-transactions-search',
  templateUrl: './f-transactions-search.component.html',
  styleUrls: ['./f-transactions-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FTransactionsSearchComponent implements OnInit {
  private itemsPerPageSet = [10, 25, 50, 100];
  currentLimit = 100;

  constructor(private _transactionService: TransactionService) {
  }

  ngOnInit() {
    this.currentLimit = this._transactionService.itemsPerPage;
  }

  setLimit(limit: number) {
    this._transactionService.setNewLimit(limit);
    console.log(this.currentLimit);
    // this.currentLimit = this._transactionService.itemsPerPage;
  }
}
