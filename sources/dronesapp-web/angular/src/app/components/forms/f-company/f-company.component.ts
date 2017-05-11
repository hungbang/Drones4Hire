import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import { AppService } from '../../../services/app.service/app.service';

@Component({
  selector: 'f-company',
  templateUrl: './f-company.component.html',
  styleUrls: ['./f-company.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientCompanyComponent implements OnInit {
  @Input() isPilot: boolean = false;
  company = {};

  constructor(
    private _accountService: AccountService,
    public _appService: AppService
  ) { }

  ngOnInit() {
    this.company = this._accountService.account.company;
  }

  onSubmit() {
  }
}
