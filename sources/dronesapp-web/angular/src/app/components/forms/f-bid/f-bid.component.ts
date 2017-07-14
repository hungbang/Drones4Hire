import {Component, ViewEncapsulation, Input, EventEmitter, Output} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';

@Component({
  selector: 'f-bid',
  templateUrl: './f-bid.component.html',
  styleUrls: ['./f-bid.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FBidComponent {
  public model: BidModel|null = null;
  @Output() send = new EventEmitter<BidModel|{}>();
  submitted: boolean = false;

  @Input()
  set bid(bid) {
    this.model = Object.assign({}, bid);
  }
  @Input() fee: number = 0;

  constructor() {
  }

  onSubmit(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.send.emit({
      bid: this.model,
      callback: () => this.clear(form)
    });
  }

  private clear(form) {
    form.resetForm();
    this.submitted = false;
  }

  get payout() {
    const num = this.model.amount * ((100 - this.fee) / 100)  + 'e+2';
    return this.fee ? +(Math.round(+num) + 'e-2') : this.model.amount;
  }
}

