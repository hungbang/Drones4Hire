import {Component, ElementRef, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';

import {RequestService} from '../../../services/request.service/request.service';
import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';

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
    private _requestService: RequestService
  ) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      this.fileURL = JSON.parse(response)['url'];
      this.fileName = this.fileItem.file.name;
      return {item, response, status, headers};
    };

    this.uploader.onErrorItem = (item, response, status, headers) => {
      console.log('problem with upload image');
      this.uploader.clearQueue();
      return {item, response, status, headers};
    };

    this.uploader.onAfterAddingFile = (item) => {
      console.log('onAfterAddingFile');

      if (!this.isLimitReached) {
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
      type: 'PHOTO'
    };
    this._portfolioService.addPortfolio(portfolio).subscribe(
      () => {
        this.resetForm(form);
      },
      err => {
        console.log('add portfolio error:', err);
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
