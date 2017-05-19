import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';
import { AccountService } from '../../../services/account.service/account.service';

@Component({
  selector: 'm-site',
  templateUrl: './m-site.component.html',
  styleUrls: ['./m-site.component.styl']
})
export class MSiteComponent implements OnInit {

  constructor(
    public _router: Router,
    public _accountService: AccountService,
    private _authorizationService: AuthorizationService
  ) { }

  nav()  {
    if (this._accountService.isUserClient()) {
      this._router.navigate(['project/add']);
    } else {
      this._router.navigate(['search']);
    }
  }
  ngOnInit() {
  }

  getCurrentUserType() {
    if (this._authorizationService.isUserLogin) {
      return this._accountService.isUserClient() ? 'client' : 'pilot';
    }
  }

  setActiveLoginForm(activeForm) {
    this._authorizationService.signUpFormActive = activeForm === 'sign-up';
  }

  logout() {
    this._authorizationService.logout();
    this._router.navigate(['/']);
  }

}

