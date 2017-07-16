import { Component, OnInit, Input} from '@angular/core';
import { AccountService } from '../../../services/account.service/account.service';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';

@Component({
  selector: 'm-footer',
  templateUrl: './m-footer.component.html',
  styleUrls: ['./m-footer.component.styl']
})
export class MFooterComponent implements OnInit {

  @Input() title: string;
  @Input() menu: any[];

  constructor(
    private _authorizationService: AuthorizationService,
    public _accountService: AccountService
  ) { }

  ngOnInit() {
  }

  getDashboardLink() { // TODO: check if its needed
    if (this._authorizationService.isUserLogin) {
      return this._accountService.isUserClient() ? '/dashboard/client/1' : '/dashboard/pilot/1';
    } else {
      return 'sign-up';
    }
  }

}
