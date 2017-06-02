import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ProjectService} from '../../../services/project.service/project.service';

@Component({
  selector: 'b-projects-search',
  templateUrl: './b-projects-search.component.html',
  styleUrls: ['./b-projects-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class BProjectsSearchComponent implements OnInit {

  constructor(
    private projectService: ProjectService
  ) { }

  ngOnInit() {
  }

  get projects() {
    return this.projectService.projects;
  }

}
