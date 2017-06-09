import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import { AccountService } from '../../../services/account.service/account.service';

@Component({
  selector: 'a-project',
  templateUrl: './a-project.component.html',
  styleUrls: ['./a-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class AProjectComponent implements OnInit {

  constructor(
    public _accountService: AccountService,
    private _authorizationService: AuthorizationService
    ) { }

  ngOnInit() {
  }

}
