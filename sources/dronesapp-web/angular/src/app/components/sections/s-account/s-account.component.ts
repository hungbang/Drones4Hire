import { Component, ViewEncapsulation } from '@angular/core';
import { AppService } from '../../../services/app.service/app.service';

@Component({
  selector: 's-account',
  templateUrl: './s-account.component.html',
  styleUrls: ['./s-account.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SAccountComponent {

  // todo Make tab with routing
  activeTabName = 'details';

  constructor(
    public _appService: AppService
  ) {}

  showTab(tabName) {
    this.activeTabName = tabName;
  }
}
