import {Component, EventEmitter, Input, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'l-project-pagination',
  templateUrl: './l-project-pagination.component.html',
  styleUrls: ['./l-project-pagination.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LProjectPaginationComponent {
  @Output() changePage = new EventEmitter<number>();
  @Input() current = 1;
  @Input() min = 1;
  @Input() max = 1;

  constructor() { }

  onChangePage(offset, event) {
    event.preventDefault();

    const next = this.current + offset;

    if (next >= this.min && next <= this.max) {
      this.changePage.emit(offset);
    }
  }
}
