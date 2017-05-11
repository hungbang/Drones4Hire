import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-project-details',
  templateUrl: './b-project-details.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-project-details.component.styl']
})
export class BProjectDetailsComponent implements OnInit {
  @Input() details: any;

  constructor() { }

  ngOnInit() {
  }

}
