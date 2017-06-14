import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 'b-project-details',
  templateUrl: './b-project-details.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-project-details.component.styl']
})
export class BProjectDetailsComponent implements OnInit {
  @Input() project: any;

  constructor(
    public projectService: ProjectService
  ) { }

  ngOnInit() {
  }

}
