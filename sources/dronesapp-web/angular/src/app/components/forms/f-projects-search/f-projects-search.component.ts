import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { ProjectService } from '../../../services/project.service/project.service';

@Component({
  selector: 'f-projects-search',
  templateUrl: './f-projects-search.component.html',
  styleUrls: ['./f-projects-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class FProjectsSearchComponent implements OnInit {
  limitProjectsToShow: number = 10;
  nameFilter: string;

  constructor(private _projectService: ProjectService) {
  }

  ngOnInit() {
  }

  refresh(limit: number): void {
    this._projectService.limitProjectsToShow = limit;
  }

  filterByName(): void {
    this._projectService.nameFilter = this.nameFilter;
  }
}
