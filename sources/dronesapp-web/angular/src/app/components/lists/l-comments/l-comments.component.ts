import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'l-comments',
  templateUrl: './l-comments.component.html',
  styleUrls: ['./l-comments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LCommentsComponent implements OnInit {
  @Input() comments: Array<{}>;
  @Input() showRating: boolean = false;

  constructor() { }

  ngOnInit() {
  }

}
