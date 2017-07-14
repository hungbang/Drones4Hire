import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {ProjectService} from '../../../services/project.service/project.service';
import {UnSubscribeDirective} from '../../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 's-my-projects',
  templateUrl: './s-my-projects.component.html',
  styleUrls: ['./s-my-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SMyProjectsComponent extends UnSubscribeDirective implements OnInit {
  public projects;
  public currentPage = 1;
  public minPage = 1;
  public maxPage = 1;
  public rows;
  private refresh = 1;

  public countPerPage: number;
  public title: string;

  private pageLink: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService
  ) {
    super();
  }

  ngOnInit() {
    this.route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(() => {
        const currentPage = parseInt(this.route.snapshot.params['page'], 10);
        const res = this.route.snapshot.data['projects'];
        const projects = res.results;
        const pageLink = this.route.snapshot.data['pageLink'];

        if (!projects || (!projects.length && currentPage > 1)) {
          return this.router.navigate([pageLink, '1']);
        }

        this.pageLink = pageLink;
        this.currentPage = currentPage;

        this.update(res, projects);
      });
  }

  changePage(page) {
    this.currentPage = page;

    this.router.navigate([this.pageLink, this.currentPage], { queryParams: this.route.snapshot.queryParams });
  }

  search({ title, countPerPage }) {
    this.title = title;
    this.countPerPage = countPerPage;

    this.refresh = this.refresh ? null : 1;

    const queryParams = {
      title, count: countPerPage, refresh: this.refresh
    };

    if (!queryParams.refresh) {
      delete queryParams.refresh;
    }
    if (!title) {
      delete queryParams.title;
    }

    const page = this.currentPage;

    this.router.navigate([this.pageLink, page], {
      queryParams
    }).then(() => {
      const res = this.route.snapshot.data['projects'];
      const projects = res.results;

      this.update(res, projects);

      this.currentPage = 0;
      setTimeout(() => {
        this.currentPage = page;
      });
    })
  }

  update(res, projects) {
    this.projects = this.projectService.formatMyProjects(projects);
    this.countPerPage = this.projectService.limitProjectsToShow;
    this.title = this.route.snapshot.queryParams['title'];

    this.maxPage = Math.ceil(res.totalResults / this.countPerPage);
  }
}
