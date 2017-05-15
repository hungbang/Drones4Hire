import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

@Component({
  selector: 'l-bids',
  templateUrl: './l-bids.component.html',
  styleUrls: ['./l-bids.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LBidsComponent implements OnInit {
  @Input() bids;

  constructor(
  ) { }

  ngOnInit() {}
}
