import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 'l-rating',
  templateUrl: './l-rating.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./l-rating.component.styl']
})
export class LRatingComponent implements OnInit {
  @Input() rating;
  ratingArr = Object.keys(new Int8Array(5)).map(Number);
  constructor() { }

  ngOnInit() {
  }

}
