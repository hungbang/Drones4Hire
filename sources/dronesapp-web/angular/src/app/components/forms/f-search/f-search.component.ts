import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {CategoryModel} from "../../../services/common.service/category.interface";

@Component({
  selector: 'f-search',
  templateUrl: './f-search.component.html',
  styleUrls: ['./f-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FSearchComponent implements OnInit {
  @Output() send = new EventEmitter<any>();

  @Input() budgets;
  @Input()
  set budget(value) {
    this._budget = value;
  }

  @Input() categories: CategoryModel[];
  @Input()
  set category(value) {
    this._category = value;
  }

  @Input()
  set postcode(value) {
    this._postcode = value;
  }

  @Input()
  set range(value) {
    this._range = value;
  }

  _postcode: string;
  _budget: string;
  _category: string;
  _range: string;

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    const budget = parseInt(this._budget, 10);
    const category = parseInt(this._category, 10);
    const range = parseInt(this._range, 10);

    this.send.emit({
      postcode: parseInt(this._postcode, 10),
      budgetId: budget >= 0 ? budget : null,
      serviceCategoryId: category >= 0 ? category : null,
      range: range >= 0 ? range : null,
    });
  }
}
