import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';
import {AppService} from '../../../services/app.service/app.service';

@Component({
  selector: 'f-public',
  templateUrl: './f-public.component.html',
  styleUrls: ['./f-public.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FPublicComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    removeAfterUpload: true
  });

  private fileItem: any = null;

  public submitted: boolean = false;

  constructor(public accountService: AccountService,
              private _requestService: RequestService,
              private _appService: AppService) {

    this.uploader.onSuccessItem = (item, response, status, headers) => {
      let type = `${this._appService.toCamelCase(item.formData[0].type)}URL`;
      this.accountService.profile[type] = JSON.parse(response)['url'];
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      console.log('problem with upload image');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile');
      this.fileItem = item;
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };
  }

  ngOnInit() {
    if (this.accountService.profile) {
      return;
    }
    this.accountService.getAccountProfile();
  }

  changeSettings(e, form) {
    e.preventDefault();
    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.accountService.setAccountProfile(this.accountService.profile)
      .subscribe((res) => {
        console.log('profile is updated', res);
      });
  }

  handlePhotoUpload(type: string) {
    this.fileItem.formData.push({type});
    this.fileItem.headers.push({
      name: 'FileType',
      value: type.toUpperCase()
    });

    this.uploader.uploadAll();
  }
}
