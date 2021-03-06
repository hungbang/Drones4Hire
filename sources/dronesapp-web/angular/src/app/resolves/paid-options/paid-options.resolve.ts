import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from '../../services/project.service/project.service';

@Injectable()
export class PaidOptionsResolve implements Resolve<any> {

  constructor(public projectService: ProjectService) {}

  resolve(route: ActivatedRouteSnapshot) {
    return this.projectService.getPaidOptions();
  }
}
