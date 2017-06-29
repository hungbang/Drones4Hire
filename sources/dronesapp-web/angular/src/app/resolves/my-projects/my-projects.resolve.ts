import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";
import {createObservable} from "../../shared/common/common-methods";

@Injectable()
export class MyProjectsResolve implements Resolve<any> {
  constructor(private projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    let page = Number(route.params['page']);

    if (isNaN(page)) {
      return createObservable(null);
    }

    this.projectService.resetLimit();

    const statuses = route.data['status'];
    const title = route.queryParams['title'];
    const countOfItemsPerPage = route.queryParams['count'] || 10;

    if (countOfItemsPerPage) {
      this.projectService.limitProjectsToShow = parseInt(countOfItemsPerPage, 10);
    }

    const pageSize = this.projectService.limitProjectsToShow;

    return this.projectService.getProjects({ page, pageSize, statuses, title });
  }
}
