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
  @Input() categories: CategoryModel[];

  zipCode: string;
  budget: string = '-1';
  category: string = '-1';

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    const budget = parseInt(this.budget, 10);
    const category = parseInt(this.category, 10);

    this.send.emit({
      zipCode: parseInt(this.zipCode, 10),
      budget: budget >= 0 ? budget : null,
      category: category >= 0 ? category : null,
    });
  }
}
