import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {ProjectModel} from '../../../services/project.service/project.interface';

@Component({
  selector: 's-project-edit',
  templateUrl: './s-project-edit.component.html',
  styleUrls: ['./s-project-edit.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SProjectEditComponent implements OnInit {
  project: ProjectModel = null;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(
      () => {
        this.project = this.route.snapshot.data['project'];
      }
    );
  }

}
