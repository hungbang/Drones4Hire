import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from '../../services/project.service/project.service';

@Injectable()
export class ProjectResolve implements Resolve<any> {

  constructor(
    public projectService: ProjectService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    const id = route.params['projectId'];

    return this.projectService.getProject(id);
  }
}

