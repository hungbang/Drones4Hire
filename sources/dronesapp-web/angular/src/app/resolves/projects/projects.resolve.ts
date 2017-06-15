import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from '../../services/project.service/project.service';

@Injectable()
export class ProjectsResolve implements Resolve<any> {

  constructor(public projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    this.projectService.resetLimit();

    const status = route.data['status'];
    const pageSize = this.projectService.limitProjectsToShow;

    return this.projectService.getProjects({ status, pageSize });
  }
}
