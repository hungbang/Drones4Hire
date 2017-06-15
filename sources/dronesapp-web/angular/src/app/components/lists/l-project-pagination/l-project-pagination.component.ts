import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'l-project-pagination',
  templateUrl: './l-project-pagination.component.html',
  styleUrls: ['./l-project-pagination.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LProjectPaginationComponent implements OnInit {
  @Output() changePage = new EventEmitter<number>();
  @Input()
  set current(value: number) {
    this.currentPage = value;

    this.updatePagination(this.currentPage);
  }
  @Input() min = 1;
  @Input() max = 1;

  public currentPage: number;
  public pages: number[] = [];

  constructor() { }

  ngOnInit() {
    this.updatePagination(this.currentPage);
  }

  updatePagination(page) {
    let minPage = page - 2;
    let maxPage = page + 2;

    if (minPage < this.min && maxPage > this.max) {
      minPage = this.min;
      maxPage = this.max;
    } else {
      if (minPage < this.min) {
        const offset = Math.abs(this.min - minPage);

        maxPage += offset;
        minPage += offset;
      }
      if (maxPage > this.max) {
        const offset = maxPage - this.max;

        maxPage -= offset;
        minPage -= offset;
      }

      if (minPage < this.min) {
        minPage = this.min;
      }
    }

    const pages = [];
    for (let i = minPage; i <= maxPage; i++) {
      pages.push(i);
    }

    this.pages = pages;
  }

  onChangePage(page, event) {
    event.preventDefault();

    if (page >= this.min && page <= this.max) {
      this.changePage.emit(page);
    }
  }
}
