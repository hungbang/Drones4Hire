import {Component, Input, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'b-tabs',
  templateUrl: './b-tabs.component.html',
  styleUrls: ['./b-tabs.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BTabsComponent {
  @Input() tabs: Array<Object>;

  constructor() {
  }
}
