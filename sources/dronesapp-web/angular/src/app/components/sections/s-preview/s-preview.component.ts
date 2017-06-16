import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 's-preview',
  templateUrl: './s-preview.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./s-preview.component.styl']
})
export class SPreviewComponent implements OnInit {
  @Input() profile;
  @Input() showDescription = true;
  canEdit: boolean|null;
  bgImage: string = './assets/img/bg-profile.jpg';

  constructor(
    public _accountService: AccountService,
  ) { }

  ngOnInit() {
    this.canEdit = this._accountService.isUserPilot() && this._accountService.account && this._accountService.account.email === this.profile.email;
  }

}
