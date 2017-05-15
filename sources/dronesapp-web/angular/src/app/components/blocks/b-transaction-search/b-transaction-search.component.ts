import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-transaction-search',
  templateUrl: './b-transaction-search.component.html',
  styleUrls: ['./b-transaction-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BTransactionSearchComponent implements OnInit {
  @Input() transaction: any;

  constructor() { }

  ngOnInit() {
  }

}
