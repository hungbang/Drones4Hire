import {Component, OnInit, ViewEncapsulation, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'f-comments',
  templateUrl: './f-comments.component.html',
  styleUrls: ['./f-comments.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FCommentsComponent implements OnInit {
  @Output() send = new EventEmitter<any>();
  submitted: boolean = false;

  public comment = '';
  public textLengthLimit = 2000;

  constructor() { }

  ngOnInit() {
  }

  onSubmit(e, form) {
    e.preventDefault();
    this.submitted = true;

    if(form.invalid) {
      return;
    }

    this.send.emit({
      comment: this.comment,
      callback: () => this.clear(form)
    });


  }

  clear(form) {
    form.resetForm();
    this.submitted = false;
  }
}
