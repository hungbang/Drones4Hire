import {Injectable} from '@angular/core';

import {PaidOptionModel} from './paid-option.interface';
import {createObservable} from '../../shared/common/common-methods';
import {RequestService} from '../request.service/request.service';
import {ProjectAttachmentModel} from './project-attacment.interface';

@Injectable()
export class ProjectService {
  paidOptions: PaidOptionModel[] = [];
  paidOptionsClasses = ['_featured', '_urgent', '_sealed', '_private'];

  public projects;

  chooseTabFilter: string = 'openForBidding';
  countProjectsToShow: number[] = [5, 10, 20, 30];
  limitProjectsToShow: number = 10;
  nameFilter: string;

  constructor(
    private _requestService: RequestService
  ) {
  }

  getPaidOptions() {
    if(this.paidOptions.length) {
      return createObservable(this.paidOptions);
    }

    return this.getListOfPaidOptions();
  }
  private getListOfPaidOptions() {
    return this._requestService.fetch('get', '/projects/paidoptions')
      .map(res => {
        this.paidOptions = res;
        console.log(res);
        return this.paidOptions;
      });
  }

  public getProjects(search = {}) {
    return this._requestService.fetch('post', '/projects/search', search)
      .map(res => {
        this.projects = res.results;
        return res;
      });
  }

  // todo add notification
  public postProjects(data: any) {
    return this._requestService.fetch('post', '/projects', data);
  }

  public getProject(id: string|number) {
    return this._requestService.fetch('get', `/projects/${id}`);
  }

  public addAttachment(attachment: ProjectAttachmentModel) {
    return this._requestService.fetch('post', '/projects/results', attachment);
  }

  public deleteAttachment(id: number) {
    return this._requestService.fetch('remove', `/projects/results/${id}`);
  }
}
