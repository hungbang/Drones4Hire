import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-pagination',
  templateUrl: './b-pagination.component.html',
  styleUrls: ['./b-pagination.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BPaginationComponent implements OnInit {
  @Input() currentPage: number;
  @Input() pagesCount: number;
  @Output() newPage: EventEmitter<number> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  setNewPage(page: number) {
    this.newPage.emit(page);
  }
}
