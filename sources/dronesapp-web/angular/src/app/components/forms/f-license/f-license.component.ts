import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {FileItem, FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';

@Component({
  selector: 'f-license',
  templateUrl: './f-license.component.html',
  styleUrls: ['./f-license.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FPilotLicenseComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    removeAfterUpload: true
  });

  private fileItem: any = null;

  public submitted: boolean = false;

  constructor(public accountService: AccountService, private _requestService: RequestService) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      let type = `${item.formData[0].type}URL`;
      this.accountService.license[type] = JSON.parse(response)['url'];
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
    if (!this.accountService.license) {
      this.accountService.getAccountLicense();
    }
  }

  saveChanges(e) {
    e.preventDefault();

    this.submitted = true;

    if (!this.accountService.license || !this.accountService.license.insuranceURL || !this.accountService.license.licenseURL) {
      return;
    }

    this.accountService.setAccountLicense(this.accountService.license)
      .subscribe((res) => {
        console.log('-save license', res);
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
