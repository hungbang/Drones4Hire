import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";
import {createObservable, deleteNullOrNaN} from "../../shared/common/common-methods";
import {Observable} from "rxjs/Observable";

@Injectable()
export class FindProjectsResolve implements Resolve<any> {
  constructor(private projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    let page = Number(route.params['page']);

    if (isNaN(page)) {
      return createObservable(null);
    }

    this.projectService.resetLimit();

    const serviceCategoryId = route.queryParams['serviceCategoryId'];
    const budgetId = route.queryParams['budgetId'];
    const postcode = route.queryParams['postcode'];
    const status = 'NEW';

    const sendObj = { page, pageSize: this.projectService.limitProjectsToShow, serviceCategoryId, budgetId, postcode, status };

    deleteNullOrNaN(sendObj, 'serviceCategoryId');
    deleteNullOrNaN(sendObj, 'budgetId');
    deleteNullOrNaN(sendObj, 'postcode');

    return this.projectService.getProjects(sendObj);
  }
}
