import {Component, Input, OnInit, ViewEncapsulation, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'b-comments',
  templateUrl: './b-comments.component.html',
  styleUrls: ['./b-comments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BCommentsComponent implements OnInit {
  @Input() comments: Array<{}>;
  @Output() send = new EventEmitter<any>();

  constructor() {
  }

  ngOnInit() {
  }

  onSend(data) {
    this.send.emit(data);
  }
}
