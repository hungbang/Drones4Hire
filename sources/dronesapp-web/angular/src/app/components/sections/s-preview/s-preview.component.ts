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

  constructor(
    private _accountService: AccountService
  ) { }

  ngOnInit() {
    this.canEdit = this._accountService.isUserPilot(); // TODO: add condition: is this profile own to this pilot?
  }

}
