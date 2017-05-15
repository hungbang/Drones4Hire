import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProjectService } from '../../../services/project.service/project.service';

@Component({
  selector: 'm-my-projects',
  templateUrl: './m-my-projects.component.html',
  styleUrls: ['./m-my-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class MMyProjectsComponent implements OnInit {
  constructor(
    private _projectService: ProjectService
  ) { }

  ngOnInit() {
  }

  chooseTab(filterName: string): void {
    this._projectService.chooseTabFilter = filterName;
  }

}
