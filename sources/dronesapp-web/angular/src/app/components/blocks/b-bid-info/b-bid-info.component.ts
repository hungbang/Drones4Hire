import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-bid-info',
  templateUrl: './b-bid-info.component.html',
  styleUrls: ['./b-bid-info.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BBidInfoComponent implements OnInit {
  @Input() bidInfo;

  constructor() { }

  ngOnInit() {
  }

}
