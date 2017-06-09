import {Component, ElementRef, OnInit, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';

import {RequestService} from '../../../services/request.service/request.service';

@Component({
  selector: 'f-project-files',
  templateUrl: './f-project-files.component.html',
  styleUrls: ['./f-project-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectFilesComponent implements OnInit {
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    removeAfterUpload: true
  });

  private fileItem: any = null;
  public hasBaseDropZoneOver = false;
  public submitted: boolean = false;

  constructor(
    private _elementRef: ElementRef,
    // public accountService: AccountService,
    private _requestService: RequestService
  ) {
    this.uploader.onSuccessItem = (item, response, status, headers) => {
      let type = `${item.formData[0].type}URL`;
      console.log(JSON.parse(response)['url']);

      // this.accountService.license[type] = JSON.parse(response)['url'];
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
  }

  addFile(e) {
    e.preventDefault();

    this.submitted = true;
  }

  handlePhotoUpload(type: string, e?: any) {
    console.log(e);
    console.log(this.fileItem);
    this.fileItem.formData.push({type});
    this.fileItem.headers.push({
      name: 'FileType',
      value: type.toUpperCase()
    });

    this.uploader.uploadAll();
  }

  triggerFileClick() {
    if (this._elementRef && this._elementRef.nativeElement !== null) {
      this._elementRef.nativeElement.querySelector('[type="file"]').click();
    }
  }

  public fileOverBase(e: any): void {
    this.hasBaseDropZoneOver = e;
  }

}
