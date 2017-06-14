import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';
import * as moment from 'moment';

@Component({
  selector: 'pr-bid',
  templateUrl: './pr-bid.component.html',
  styleUrls: ['./pr-bid.component.styl'],
  encapsulation: ViewEncapsulation.None,
})
export class PrBindComponent implements OnInit {
  @Input() bid: BidModel;

  public diff;

  constructor() {
  }

  ngOnInit() {
    this.diff = this.bid.createdAt ? moment().diff(moment(this.bid.createdAt, 'x'), 'days') : 0;
  }
}
