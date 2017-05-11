import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

@Component({
  selector: 'b-review',
  templateUrl: './b-review.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-review.component.styl']
})
export class BReviewComponent implements OnInit {
  @Input() profile;
  constructor() { }

  ngOnInit() {}

}
