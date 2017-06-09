import {Component, ViewEncapsulation, Input, EventEmitter, Output} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';

@Component({
  selector: 'f-bid',
  templateUrl: './f-bid.component.html',
  styleUrls: ['./f-bid.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FBidComponent {
  public model: BidModel|{} = {};
  @Output() send = new EventEmitter<BidModel|{}>();

  @Input()
  set bid(bid) {
    this.model = Object.assign({}, bid);
  }

  constructor() {
  }

  onSubmit() {
    this.send.emit(this.model);
  }
}
