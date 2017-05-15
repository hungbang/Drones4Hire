import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { BidService } from '../../../services/bid.service/bid.service';

@Component({
  selector: 'pr-bid',
  templateUrl: './pr-bid.component.html',
  styleUrls: ['./pr-bid.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class PrBindComponent implements OnInit {
  @Input() bid: Object;
  isAccepted: boolean = false;
  buttAcceptName: string = 'Accept';

  constructor(
    public _bidService: BidService
  ) {
  }

  ngOnInit() {
  }

  accept(): void {
    this.isAccepted = true;
    this._bidService.isAccepted = true;
    this.buttAcceptName = 'Accepted';
  }
}
