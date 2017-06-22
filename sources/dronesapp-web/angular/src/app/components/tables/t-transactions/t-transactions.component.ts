import {Component, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 't-transactions',
  templateUrl: './t-transactions.component.html',
  styleUrls: ['./t-transactions.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TTransactionsComponent {
  @Input() transactions;

  constructor(
  ) {
  }

}
