import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

import * as moment from 'moment';

@Component({
  selector: 'pr-bid',
  templateUrl: './pr-bid.component.html',
  styleUrls: ['./pr-bid.component.styl'],
  encapsulation: ViewEncapsulation.None,
})
export class PrBindComponent implements OnInit {
  @Input() bid;
  @Input() fee: number = 0;
  @Input() pilot: boolean = false;

  public diff;

  constructor() {
  }

  ngOnInit() {
    this.diff = this.bid.createdAt ? moment().diff(moment(this.bid.createdAt, 'x'), 'days') : 0;
  }

  get payout() {
    const num = this.bid.amount * ((100 - this.fee) / 100)  + 'e+2';
    return this.fee ? +(Math.round(+num) + 'e-2') : this.bid.amount;
  }
}
