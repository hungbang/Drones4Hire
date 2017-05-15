import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-comments',
  templateUrl: './b-comments.component.html',
  styleUrls: ['./b-comments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BCommentsComponent implements OnInit {
  @Input() comments: Array<{}>;

  constructor() { }

  ngOnInit() {
  }

}
