import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {AppService} from '../../../services/app.service/app.service';
import {User} from '../../../classes/User.class/User.class';
import {AuthorizationService} from '../../../services/authorization.service/authorization.service';
import {FileItem, FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';

@Component({
  selector: 'f-profile',
  templateUrl: './f-profile.component.html',
  styleUrls: ['./f-profile.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FClientProfileComponent implements OnInit {
  account: any;
  uploadedPhoto: any;

  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    headers: [{
      name: 'FileType',
      value: 'USER_PHOTO'
    }]
  });

  constructor(private _accountService: AccountService,
              private _authorizationService: AuthorizationService,
              public _appService: AppService,
              private _requestService: RequestService) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      console.log('onSuccessItem', response);
      console.log(JSON.parse(response));
      this.account['photoURL'] = JSON.parse(response)['url'];
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      console.log('problem with upload image');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile');
      this.uploader.queue[0].upload();
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };
  }

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

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }

  saveChanges() {
    this._accountService.saveUserData(this.account)
      .then((res) => {
        console.log(res);
      });
  }
}
