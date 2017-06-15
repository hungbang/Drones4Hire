import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";
import {createObservable} from "../../shared/common/common-methods";

@Injectable()
export class PilotProjectsResolve implements Resolve<any> {
  constructor(
    private projectService: ProjectService
  ) {

  }

  resolve(route: ActivatedRouteSnapshot) {
    let page = Number(route.params['page']);

    if (isNaN(page)) {
      return createObservable(null);
    }

    this.projectService.resetLimit();

    const sendObj = {page, pageSize: this.projectService.limitProjectsToShow};

    return this.projectService.getProjects(sendObj);
  }
}
