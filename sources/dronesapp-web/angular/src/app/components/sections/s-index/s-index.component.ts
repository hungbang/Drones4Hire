import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../../services/account.service/account.service';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';

@Component({
  selector: 's-index',
  templateUrl: './s-index.component.html',
  styleUrls: ['./s-index.component.styl']
})
export class SIndexComponent implements OnInit {

  constructor(
    public _accountService: AccountService,
    public _authorizationService: AuthorizationService,
  ) { }

  ngOnInit() {
  }

}
