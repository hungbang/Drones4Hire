import {Component, Input, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'b-tabs',
  templateUrl: './b-tabs.component.html',
  styleUrls: ['./b-tabs.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BTabsComponent {
  @Input() tabs: Array<Object>;
  @Input() classes: string;

  constructor() {
  }
}
