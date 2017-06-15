import {Component, OnInit, ViewEncapsulation, Input, Output, EventEmitter} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';

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
  @Input() attachmentsLength: number;
  @Output() award = new EventEmitter<number>();

  constructor() {

  }

  ngOnInit() {
  }

  onAward(id) {
    this.award.emit(id);
  }
}
