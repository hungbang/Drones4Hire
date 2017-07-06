import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {AccountService} from '../../../services/account.service/account.service';
import {ProjectModel} from '../../../services/project.service/project.interface';
import {ProjectAttachmentModel} from '../../../services/project.service/project-attacment.interface';
import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 'b-project-files',
  templateUrl: './b-project-files.component.html',
  styleUrls: ['./b-project-files.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectFilesComponent implements OnInit {
  files: Array<any> = [];
  project: ProjectModel;
  public deleteFunc = (e) => { this.deleteFile(e) };

  constructor(
    public accountService: AccountService,
    private _route: ActivatedRoute,
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    this._route.params.subscribe(
      () => {
        this.project = this._route.snapshot.parent.data['project'];
        this.fetchProjectFiles();
      }
    );
  }

  private fetchProjectFiles() {
    if (this.project && this.project.attachments && this.project.attachments.length) {
      this.files = this.project.attachments.filter(
        (attach: any) => {
          if (attach.type === 'PROJECT_RESULT') {
            return attach.itemURL = attach.attachmentURL;
          }
        }
      );
    }
  }

  addFile(attachment: ProjectAttachmentModel) {
    const fileItem = {
      itemURL: attachment.attachmentURL,
      title: attachment.title,
      id: attachment.id
    };
    this.files.push(fileItem);
  }

  deleteFile(id) {
    this.projectService.deleteAttachment(id)
      .subscribe(
        () => {
          this.files = this.files.filter(file => file.id !== id);
        },
        err => {
          console.log('delete attachment error:', err);
        }
      );
  }

}
