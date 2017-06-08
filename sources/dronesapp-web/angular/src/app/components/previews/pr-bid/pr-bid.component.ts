import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';
import {BidModel} from '../../../services/bid.service/bid.interface';

@Component({
  selector: 'pr-bid',
  templateUrl: './pr-bid.component.html',
  styleUrls: ['./pr-bid.component.styl'],
  encapsulation: ViewEncapsulation.None,
})
export class PrBindComponent implements OnInit {
  @Input() bid: BidModel;

  constructor() {
  }

  ngOnInit() {
  }
}
