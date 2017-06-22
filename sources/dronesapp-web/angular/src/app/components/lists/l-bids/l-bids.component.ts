import {Component, OnInit, ViewEncapsulation, Input, Output, EventEmitter} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';
import * as moment from 'moment';
import {PaymentService} from "../../../services/payment.service/payment.service";

@Component({
  selector: 'l-bids',
  templateUrl: './l-bids.component.html',
  styleUrls: ['./l-bids.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LBidsComponent implements OnInit {
  @Input() bids: BidModel[];
  @Input() activeId: number;
  @Input() disabled: boolean;
  @Input() canRelease: number;
  @Output() award = new EventEmitter<number>();
  @Output() release = new EventEmitter<number>();

  constructor() {

  }

  ngOnInit() {
  }

  onAward(id) {
    this.award.emit(id);
  }

  onRelease(bidId: number) {
    this.release.emit(bidId);
  }
}
