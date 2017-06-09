import {Component, OnInit, ViewEncapsulation, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'f-comments',
  templateUrl: './f-comments.component.html',
  styleUrls: ['./f-comments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FCommentsComponent implements OnInit {
  @Output() send = new EventEmitter<any>();

  public comment = '';

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    this.send.emit({
      comment: this.comment,
      callback: () => this.clear()
    });
  }

  clear() {
    this.comment = '';
  }
}
