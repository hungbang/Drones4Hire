import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 's-project',
  templateUrl: './s-project.component.html',
  styleUrls: ['./s-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SProjectComponent implements OnInit {
  tabs: Array<any> = null;
  project: any;
  canUpload: boolean = false;
  approvedStatuses = ['IN_PROGRESS', 'COMPLETED'];

  constructor(
    private _route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.project = this._route.snapshot.data['project'];
    this.checkUploadAccess();

    this.tabs = [
      {
        link: 'description',
        text: 'Project description',
        visibility: true,
        icon: 'list'
      },
      {
        link: 'files',
        text: 'Project files',
        visibility: this.canUpload,
        icon: 'folder'
      }
    ]
  }

  private checkUploadAccess() {
    this.canUpload = (this.project && this.project.status && this.approvedStatuses.indexOf(this.project.status) !== -1);
  }

}
