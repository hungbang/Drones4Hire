import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {NgProgressService} from 'ngx-progressbar';

import {AccountService} from '../../../services/account.service/account.service';
import {RequestService} from '../../../services/request.service/request.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

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
  public licFileName: string = '';
  public certFileName: string = '';
  public uploadedLicFileName: string = '';
  public uploadedCertFileName: string = '';

  public submitted: boolean = false;
  private licenseCountries: any[] = [
    13, // Australia
    40, // Canada
    76, // France
    83, // Germany
    106, // Ireland
    141, // Mexico
    155, // New Zealand
    192, // Singapore
    224, // United Kingdom
    225, // United States
  ];

  constructor(
    public accountService: AccountService,
    private _requestService: RequestService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      this.progressbarService.done();
      let type = `${item.formData[0].type}URL`;

      if (type === 'licenseURL') {
        this.licFileName = item.file.name;
      } else if (type === 'insuranceURL') {
        this.certFileName = item.file.name;
      }
      this.accountService.license[type] = JSON.parse(response)['url'];
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      this.progressbarService.done();
      this.toastrService.showError('Can\'t upload image. Please try one more time.');
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
      this.accountService.getAccountLicense()
        .subscribe(
          () => {
            // console.log(this.accountService.license);
            this.fetchUploadedFilenames();
          }
        );
    } else {
      this.fetchUploadedFilenames();
    }
  }

  saveChanges(e) {
    e.preventDefault();

    this.submitted = true;

    if (!this.accountService.license || !this.accountService.license.insuranceURL || !this.accountService.license.licenseURL) {
      return;
    }

    this.progressbarService.start();
    this.accountService.setAccountLicense(this.accountService.license)
      .subscribe(
        (res) => {
          this.progressbarService.done();
          console.log('-save license', res);
          this.updateFilenames();
          this.toastrService.showSuccess('Submitted')
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else {
            this.toastrService.showError('Please check your data');
          }
        }
      );
  }

  handlePhotoUpload(type: string) {
    this.fileItem.formData.push({type});
    this.fileItem.headers.push({
      name: 'FileType',
      value: type.toUpperCase()
    });
    this.progressbarService.start();
    this.uploader.uploadAll();
  }

  get isRequiredLicense() {
    return this.accountService.account.location.country && this.licenseCountries.some(country_id => country_id === this.accountService.account.location.country.id);
  }

  private fetchUploadedFilenames() {
    this.uploadedLicFileName = this.accountService.license.licenseURL ? this.accountService.license.licenseURL.split('/').pop().split('-').splice(2).join('-') : '';
    this.uploadedCertFileName = this.accountService.license.insuranceURL ? this.accountService.license.insuranceURL.split('/').pop().split('-').splice(2).join('-') : '';
  }

  private updateFilenames() {
    this.fetchUploadedFilenames();
    this.licFileName = '';
    this.certFileName = '';
  }
}
