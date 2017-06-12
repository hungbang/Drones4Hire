import {Component, EventEmitter, Input, Output, ViewEncapsulation} from '@angular/core';
import {BidService} from "../../../services/bid.service/bid.service";

@Component({
  selector: 'f-projects-search',
  templateUrl: './f-projects-search.component.html',
  styleUrls: ['./f-projects-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectsSearchComponent {
  @Input()
  set countPerPage(value: number) {
    this._countPerPage = value;
  }
  @Input()
  set title(value: string) {
    this._title = value;
  }
  @Output() search = new EventEmitter<any>();

  public _title: string;
  public _countPerPage: number;

  constructor(private bidService: BidService) {
  }

  get countOfItemsSelection() {
    return this.bidService.countOfItemsSelection;
  }

  onSearch() {
    const search = Object.assign({}, {
      title: this._title,
      countPerPage: this._countPerPage
    });


    this.search.emit(search);
  }
}
