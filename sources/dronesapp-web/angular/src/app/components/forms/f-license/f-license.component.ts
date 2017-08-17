import {Component, ElementRef, OnInit, ViewEncapsulation} from '@angular/core';
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
  private fileNameLengthLimit: number = 70;
  public fileNameLengthLimitError: string = '';

  public submitted: boolean = false;

  constructor(
    public accountService: AccountService,
    private _requestService: RequestService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService,
    private el: ElementRef
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
            if (this.isNeedUpload) {
              this.scrollToForm();
            }
          }
        );
    } else {
      this.fetchUploadedFilenames();
      if (this.isNeedUpload) {
        this.scrollToForm();
      }
    }
  }

  saveChanges(e, form) {
    e.preventDefault();

    this.submitted = true;

    if ((this.isNeedUpload && !this.licFileName) || (!this.licFileName && !this.certFileName)) {
      return;
    }

    this.fileNameLengthLimitError = '';
    this.progressbarService.start();
    this.accountService.setAccountLicense(this.accountService.license)
      .subscribe(
        (res) => {
          this.progressbarService.done();
          console.log('-save license', res);
          this.updateFilenames();
          this.toastrService.showSuccess('Submitted');
          this.submitted = false;
          form.resetForm();
        },
        err => {
          this.progressbarService.done();
          console.log(err);
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else {
            if (err.status === 400) {
              const body = err.json();
              if (body && body.validationErrors) {
                body.validationErrors.forEach(item => {
                  this.toastrService.showError(item.field);
                });
              } else {
                this.toastrService.showError('Can\'t save file(s). Please try again');
              }
            } else {
              this.toastrService.showError('Can\'t save file(s). Please try again');
            }
          }
        }
      );
  }

  handlePhotoUpload(type: string) {
    if (this.fileItem.file.name.length > this.fileNameLengthLimit) {
      this.fileNameLengthLimitError = type;
      this.uploader.removeFromQueue(this.fileItem);
      this.fileItem = null;
    } else {
      this.fileNameLengthLimitError = '';
      this.fileItem.formData.push({type});
      this.fileItem.headers.push({
        name: 'FileType',
        value: type.toUpperCase()
      });
      this.progressbarService.start();
      this.uploader.uploadAll();
    }
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

  get isNeedUpload() {
    const isPilot = this.accountService.isUserPilot();
    const isGotLicenseInfo = !!this.accountService.license;
    const isVerified = isGotLicenseInfo ? this.accountService.license.verified : false;

    return isPilot && !isVerified;
  }

  private scrollToForm() {
    const rect = this.el.nativeElement.getBoundingClientRect();

    setTimeout(() => {
      window.scrollTo(0, rect.top + document.body.scrollTop - 150);
    }, 1);
  }
}
