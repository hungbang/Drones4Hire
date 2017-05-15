import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';

@Component({
  selector: 'b-project-search',
  templateUrl: './b-project-search.component.html',
  styleUrls: ['./b-project-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectSearchComponent implements OnInit {

  @Input() project: Object;
  constructor() { }

  ngOnInit() {
  }

}
