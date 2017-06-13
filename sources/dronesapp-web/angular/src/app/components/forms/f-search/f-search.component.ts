import {Component, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'f-search',
  templateUrl: './f-search.component.html',
  styleUrls: ['./f-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FSearchComponent implements OnInit {
  @Output() send = new EventEmitter<any>();

  @Input() budgets;

  zipCode: string;
  budget: string = '-1';

  constructor() { }

  ngOnInit() {
  }

  onSubmit() {
    const budget = parseInt(this.budget, 10);

    this.send.emit({
      zipCode: parseInt(this.zipCode, 10),
      budget: budget >= 0 ? budget : null
    });
  }
}
