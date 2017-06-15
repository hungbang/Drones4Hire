import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectModel} from "../../../services/project.service/project.interface";
import {ProjectService} from "../../../services/project.service/project.service";

@Component({
  selector: 's-dashboard',
  templateUrl: './s-dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./s-dashboard.component.styl']
})
export class SDashboardComponent implements OnInit {
  public currentPage: number;
  public projects: ProjectModel[];

  private pageLink: string;

  public minPage = 1;
  public maxPage = 1;
  public userType;

  constructor(
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router,
  ) { }

  ngOnInit() {
    this.userType = this.route.snapshot.data['userType'];
    this.route.params.subscribe(() => {
      const page = Number(this.route.snapshot.params['page']);
      const pageLink = this.route.snapshot.data['pageLink'];
      const res = this.route.snapshot.data['projects'];

      const projects = res && res.results;

      if ((!projects || !projects.length) && !isNaN(page) && page > 1 || isNaN(page)) {
        return this.router.navigate([pageLink, 1]);
      }

      this.update(res, projects);

      this.pageLink = pageLink;
      this.currentPage = page;
    });
  }

  update(res, projects) {
    this.projects = this.userType === 'client'
      ? this.projectService.formatClientDashboardProjects(projects)
      : this.projectService.formatPilotDashboardProjects(projects);

    this.maxPage = Math.ceil(res.totalResults / this.projectService.limitProjectsToShow);
  }

  changePage(page) {
    this.currentPage = page;
    this.router.navigate([this.pageLink, this.currentPage]);
  }
}
