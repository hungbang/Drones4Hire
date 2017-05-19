import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import { AppService } from '../../../services/app.service/app.service';
import { User } from '../../../classes/User.class/User.class';
import { AuthorizationService } from '../../../services/authorization.service/authorization.service';

@Component({
  selector: 'f-profile',
  templateUrl: './f-profile.component.html',
  styleUrls: ['./f-profile.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientProfileComponent implements OnInit {
  account: any;
  uploadedPhoto: any;

  constructor(
    private _accountService: AccountService,
    private _authorizationService: AuthorizationService,
    public _appService: AppService
  ) { }

  ngOnInit() {
    this.account = new User().getInitialUser();
    this.account = Object.assign(this.account, this._accountService.currentUser);
    this.account = this.deleteUnusedProperty(Object.assign({}, this.account));
  }

  deleteUnusedProperty(account) {
    delete account.groups;
    delete account.email;
    delete account.confirmed;
    delete account.enabled;
    delete account.username;
    delete account.id;
    return account;
  }

  handlePhotoUpload(e) {
    const a = e.target.files;
  }

  saveChanges() {
    this._accountService.saveUserData(this.account)
      .then((res) => {
        console.log('success');
      });
  }

  onSubmit() {
  }
}
