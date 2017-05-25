import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AccountService} from '../../../services/account.service/account.service';
import {FileUploader} from 'ng2-file-upload';
import {RequestService} from '../../../services/request.service/request.service';

@Component({
  selector: 'f-license',
  templateUrl: './f-license.component.html',
  styleUrls: ['./f-license.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FPilotLicenseComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/license`,
    authToken: this._requestService.getCurrentToken()
  });

  public submitted: boolean = false;

  constructor(public accountService: AccountService, private _requestService: RequestService) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      debugger;
      console.log('onSuccessItem', response);
      console.log(JSON.parse(response));
      this.accountService.account['photoURL'] = JSON.parse(response)['url'];
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
      debugger;
      // this.uploader.queue[0].upload();
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      debugger;
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };
  }

  ngOnInit() {
    this.accountService.getUserLicense()
      .subscribe((res) => {
        console.log(res)
      });
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (form.invalid) {
      return;
    }

    this.uploader.uploadAll();

    // this.accountService.setUserLicense(this.accountService.license)
    //   .subscribe((res) => {
    //     console.log(res, '-save license');
    //   });
  }

  handlePhotoUpload() {
    this.uploader.uploadAll();
  }
}
