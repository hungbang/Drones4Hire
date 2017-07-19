import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 'l-portfolio-files',
  templateUrl: './l-portfolio-files.component.html',
  styleUrls: ['./l-portfolio-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class LPortfolioFilesComponent implements OnInit {
  @Input() files: Array<any> = [];
  @Input() deleteFunc: Function = () => {};

  constructor(
    public accountService: AccountService
  ) { }

  ngOnInit() {
  }

  get isPilot() {
    return this.accountService.isUserPilot();
  }

  get isClient() {
    return this.accountService.isUserClient();
  }

}
