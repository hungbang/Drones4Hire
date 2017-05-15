import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'l-pagination',
  templateUrl: './l-pagination.component.html',
  styleUrls: ['./l-pagination.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LPaginationComponent implements OnInit {
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
