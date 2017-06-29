import {Component, ViewEncapsulation, Input, Output, EventEmitter} from '@angular/core';

import {BidModel} from '../../../services/bid.service/bid.interface';

@Component({
  selector: 'l-bids',
  templateUrl: './l-bids.component.html',
  styleUrls: ['./l-bids.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LBidsComponent {
  @Input() bids: BidModel[];
  @Input() activeId: number;
  @Input() disabled: boolean;
  @Input() canRelease: number;
  @Output() award = new EventEmitter<number>();
  @Output() release = new EventEmitter<number>();

  constructor() {
  }

  onAward(id) {
    this.award.emit(id);
  }

  onRelease(projectId: number) {
    this.release.emit(projectId);
  }
}
