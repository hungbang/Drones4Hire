import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Resolve} from '@angular/router';

import {ProjectService} from "../../services/project.service/project.service";
import {AccountService} from "../../services/account.service/account.service";
import {createObservable} from "../../shared/common/common-methods";

@Injectable()
export class ClientProjectsResolve implements Resolve<any> {
  constructor(
    private projectService: ProjectService,
    private accountService: AccountService
  ) {

  }

  resolve(route: ActivatedRouteSnapshot) {
    const page = Number(route.params['page']);

    if (isNaN(page)) {
      return createObservable(null);
    }

    this.projectService.resetLimit();

    const clientId = this.accountService.account.id;
    const sendObj = {page, pageSize: this.projectService.limitProjectsToShow, clientId};

    return this.projectService.getProjects(sendObj);
  }
}
