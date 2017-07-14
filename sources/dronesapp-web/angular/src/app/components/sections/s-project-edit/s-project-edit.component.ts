import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {ProjectModel} from '../../../services/project.service/project.interface';
import {UnSubscribeDirective} from '../../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 's-project-edit',
  templateUrl: './s-project-edit.component.html',
  styleUrls: ['./s-project-edit.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SProjectEditComponent extends UnSubscribeDirective implements OnInit {
  project: ProjectModel = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {
    super();
  }

  ngOnInit() {
    this.route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(
        () => {
          this.project = this.route.snapshot.data['project'];

          if (!this.project || this.project.status !== 'NEW') {
            this.router.navigate(['/my-projects']);
          }
        }
      );
  }

}
