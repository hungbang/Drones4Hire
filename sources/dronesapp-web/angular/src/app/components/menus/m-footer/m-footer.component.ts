import { Component, OnInit, Input} from '@angular/core';
import { AccountService } from '../../../services/account.service/account.service';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';

@Component({
  selector: 'm-footer',
  templateUrl: './m-footer.component.html',
  styleUrls: ['./m-footer.component.styl']
})
export class MFooterComponent implements OnInit {

  @Input() isNav: boolean ;
  @Input() isSupport: boolean ;
  @Input() isTouch: boolean ;

  constructor(
    private _authorizationService: AuthorizationService,
    public _accountService: AccountService
  ) { }

  ngOnInit() {
  }

  getDashboardLink() {
    if (this._authorizationService.isUserLogin) {
      return this._accountService.isUserClient() ? '/dashboard/client' : '/dashboard/pilot';
    } else {
      return 'sign-up';
    }
  }

}
