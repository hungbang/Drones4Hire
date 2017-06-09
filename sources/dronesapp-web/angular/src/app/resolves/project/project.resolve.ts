import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, Router} from '@angular/router';

import {ProjectService} from '../../services/project.service/project.service';
import {Observable} from 'rxjs';

@Injectable()
export class ProjectResolve implements Resolve<any> {

  constructor(
    public projectService: ProjectService
  ) {}

  resolve(route: ActivatedRouteSnapshot, ...b) {
    const id = route.params['projectId'];

    return this.projectService.getProject(id);
  }
}

