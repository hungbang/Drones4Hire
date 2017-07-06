import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from '../../services/project.service/project.service';
import {createObservable, deleteNullOrNaN} from '../../shared/common/common-methods';

@Injectable()
export class FindProjectsResolve implements Resolve<any> {
  constructor(
    private projectService: ProjectService
  ) {}

  resolve(route: ActivatedRouteSnapshot) {
    let page = Number(route.params['page']);

    if (isNaN(page)) {
      return createObservable(null);
    }

    this.projectService.resetLimit();

    const serviceCategoryId = route.queryParams['serviceCategoryId'];
    const budgetId = route.queryParams['budgetId'];
    const postcode = route.queryParams['postcode'];
    const range = parseInt(route.queryParams['range'], 10);
    const statuses = ['NEW'];

    const sendObj = { page, pageSize: this.projectService.limitProjectsToShow, serviceCategoryId, budgetId, postcode, statuses, range };

    deleteNullOrNaN(sendObj, 'serviceCategoryId');
    deleteNullOrNaN(sendObj, 'budgetId');
    deleteNullOrNaN(sendObj, 'postcode');
    deleteNullOrNaN(sendObj, 'range');

    return this.projectService.getProjects(sendObj);
  }
}
