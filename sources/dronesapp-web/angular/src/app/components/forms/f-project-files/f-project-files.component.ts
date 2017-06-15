import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewEncapsulation} from '@angular/core';
import {FileUploader} from 'ng2-file-upload';

import {RequestService} from '../../../services/request.service/request.service';
import {ProjectAttachmentModel} from '../../../services/project.service/project-attacment.interface';
import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 'f-project-files',
  templateUrl: './f-project-files.component.html',
  styleUrls: ['./f-project-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectFilesComponent implements OnInit {
  @Input() projectID: number;
  @Output() fileAttached: EventEmitter<ProjectAttachmentModel> = new EventEmitter();
  public uploader: FileUploader = new FileUploader({
    url: `${this._requestService.apiUrl}/upload`,
    authToken: this._requestService.getCurrentToken(),
    removeAfterUpload: true,
    headers: [{
      name: 'FileType',
      value: 'PROJECT_RESULT'
    }]
  });

  private fileItem: any = null;
  public hasBaseDropZoneOver = false;
  public submitted: boolean = false;
  fileURL: string = '';
  fileName: string = '';
  title: string = '';

  constructor(
    private _elementRef: ElementRef,
    private _requestService: RequestService,
    private projectService: ProjectService
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
      this.fileItem = item;
      this.uploader.uploadAll();
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

    if (!this.title || !this.fileURL) {
      return;
    }

    const attachment = {
      attachmentURL: this.fileURL,
      projectId: this.projectID,
      title: this.title,
      type: 'PROJECT_RESULT'
    };
    this.projectService.addAttachment(attachment)
      .subscribe(
        () => {
          this.fileAttached.emit(attachment);
          this.resetForm(form);
        },
        err => {
          console.log('project attachment error', err);
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
}
