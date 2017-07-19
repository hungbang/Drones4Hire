import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Event as RouterEvent, Router} from '@angular/router';

import {UnSubscribeDirective} from '../../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 's-project',
  templateUrl: './s-project.component.html',
  styleUrls: ['./s-project.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SProjectComponent extends UnSubscribeDirective implements OnInit {
  tabs: Array<any> = null;
  project: any;
  canUpload: boolean = false;
  approvedStatuses = ['IN_PROGRESS', 'COMPLETED'];

  constructor(
    private _route: ActivatedRoute,
    private _router: Router
  ) {
    super();
  }

  ngOnInit() {
    this.initData();

    this._router.events
      .takeUntil(this.ngUnSubscribe)
      .filter((route) => route instanceof NavigationEnd)
      .subscribe(
        (event: RouterEvent) => {
          if (!this.project || this.project.id !== +this._route.snapshot.params['projectId']) {
            this.initData();
          }
        }
      );

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

  private initData() {
    this.project = this._route.snapshot.data['project'];
    this.checkUploadAccess();
  }

  private checkUploadAccess() {
    this.canUpload = (this.project && this.project.status && this.approvedStatuses.indexOf(this.project.status) !== -1);
  }

}
