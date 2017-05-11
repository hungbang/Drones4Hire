import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';
import { AppService } from '../../../services/app.service/app.service';

@Component({
  selector: 'f-profile',
  templateUrl: './f-profile.component.html',
  styleUrls: ['./f-profile.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientProfileComponent implements OnInit {
  @Input() isPilot: boolean = false;
  account = {};

  constructor(
    private _accountService: AccountService,
    public _appService: AppService
  ) { }

  ngOnInit() {
    this.account = this._accountService.account.general;
  }

  onSubmit() {
  }
}
