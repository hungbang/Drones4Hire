import {Component, ElementRef, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';
import {NgProgressService} from 'ngx-progressbar';

import {RequestService} from '../../../services/request.service/request.service';
import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';
import {ToastrService} from '../../../services/toastr.service/toastr.service';

@Component({
  selector: 'f-portfolio-upload',
  templateUrl: './f-portfolio-upload.component.html',
  styleUrls: ['./f-portfolio-upload.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FPortfolioUploadComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    removeAfterUpload: true,
    headers: [{
      name: 'FileType',
      value: 'PORTFOLIO'
    }]
  });

  private fileItem: any = null;
  fileURL: string = '';
  fileName: string = '';
  title: string = '';
  public hasBaseDropZoneOver = false;
  public submitted: boolean = false;
  attachmentsLimit = 11;

  constructor(
    private _elementRef: ElementRef,
    private _portfolioService: PortfolioService,
    private _requestService: RequestService,
    private toastrService: ToastrService,
    private progressbarService: NgProgressService
  ) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      this.progressbarService.done();
      this.fileURL = JSON.parse(response)['url'];
      this.fileName = this.fileItem.file.name;
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      this.progressbarService.done();
      console.log('problem with upload image');
      this.toastrService.showError('Couldn\'t upload image. Try one more time.');
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile');

      if (!this.isLimitReached) {
        this.progressbarService.start();
        this.fileItem = item;
        this.uploader.uploadAll();
      } else {
        this.uploader.clearQueue();
      }
      return {item};
    };

    this.uploader.onCancelItem = (item, response, status, headers) => {
      console.log('onWhenAddingFileFailed');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };
  }

  ngOnInit() {
  }

  addFile(e, form) {
    e.preventDefault();

    this.submitted = true;

    if (!this.title || !this.fileURL || this.isLimitReached) {
      return;
    }

    const portfolio = {
      title: this.title,
      itemURL: this.fileURL,
      name: this.fileName,
      type: 'PHOTO'
    };
    this.progressbarService.start();
    this._portfolioService.addPortfolio(portfolio).subscribe(
      () => {
        this.progressbarService.done();
        this.resetForm(form);
        this.toastrService.showSuccess('File added')
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
              this.toastrService.showError('Can\'t save file. Please try again');
            }
          } else {
            this.toastrService.showError('Can\'t save file. Please try again');
          }
        }
      }
    );
  }

  triggerFileClick() {
    if (this._elementRef && this._elementRef.nativeElement !== null) {
      this._elementRef.nativeElement.querySelector('[type="file"]').click();
    }
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

  private resetForm(form) {
    form.resetForm();
    this.fileURL = '';
    this.fileName = '';
    this.title = '';
    this.submitted = false;
  }

  get isLimitReached() {
    return this._portfolioService.items.length >= this.attachmentsLimit;
  }
}
