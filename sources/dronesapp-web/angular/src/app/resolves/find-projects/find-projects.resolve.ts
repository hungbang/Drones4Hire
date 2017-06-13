import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";
import {deleteNullOrNaN} from "../../shared/common/common-methods";

@Injectable()
export class FindProjectsResolve implements Resolve<any> {
  constructor(private projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    const page = parseInt(route.params['page'], 10);

    const category_id = route.queryParams['category_id'];
    const budget_id = route.queryParams['budget_id'];
    const postcode = route.queryParams['postcode'];

    const sendObj = { page: 1, pageSize: 10, category_id, budget_id, postcode };

    deleteNullOrNaN(sendObj, 'category_id');
    deleteNullOrNaN(sendObj, 'budget_id');
    deleteNullOrNaN(sendObj, 'postcode');

    return this.projectService.getProjects(sendObj);
  }
}
