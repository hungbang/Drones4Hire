import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';

import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 'b-similar',
  templateUrl: './b-similar.component.html',
  styleUrls: ['./b-similar.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BSimilarComponent implements OnInit {
  @Input() item;

  constructor(
    public projectService: ProjectService
  ) { }

  ngOnInit() {
  }

}
