import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 't-search',
  templateUrl: './t-search.component.html',
  styleUrls: ['./t-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TSearchComponent implements OnInit {
  @Input() projects: any[] = [];

  constructor() {
  }

  ngOnInit() {
  }

}
