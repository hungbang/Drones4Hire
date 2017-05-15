import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'transactions-page',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TransactionsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
