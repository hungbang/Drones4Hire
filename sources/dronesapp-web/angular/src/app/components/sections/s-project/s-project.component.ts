import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 's-project',
  templateUrl: './s-project.component.html',
  styleUrls: ['./s-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SProjectComponent implements OnInit {
  tabs: Array<Object> = null;
  project: any;
  approvedStatuses = ['IN_PROGRESS', 'COMPLETED'];

  constructor(
    private _route: ActivatedRoute
  ) {
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
        visibility: !this.canUpload(), // TODO: invert after this tab ready
        icon: 'folder'
      }
    ]
  }

  ngOnInit() {
    this.project = this._route.snapshot.data['project'];
  }

  private canUpload() {
    return (this.project && this.project.status && this.approvedStatuses.indexOf(this.project.status) !== -1)
  }

}
