import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";

@Injectable()
export class MyProjectsResolve implements Resolve<any> {
  constructor(private projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    const status = route.data['status'];
    const page = parseInt(route.params['page'], 10);

    const title = route.queryParams['title'];
    const countOfItemsPerPage = route.queryParams['count'] || 10;

    if (countOfItemsPerPage) {
      this.projectService.limitProjectsToShow = parseInt(countOfItemsPerPage, 10);
    }

    const pageSize = this.projectService.limitProjectsToShow;

    return this.projectService.getProjects({ page, pageSize, status, title });
  }
}
