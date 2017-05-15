import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {BidService} from '../../../services/bid.service/bid.service';

@Component({
  selector: 'b-bids',
  templateUrl: './b-bids.component.html',
  styleUrls: ['./b-bids.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BBidsComponent implements OnInit {
  @Input() bids;
  constructor(
    private _bidService: BidService
  ) { }

  ngOnInit() {
  }

}
