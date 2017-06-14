import {Injectable} from '@angular/core';

import {PaidOptionModel} from './paid-option.interface';
import {createObservable} from '../../shared/common/common-methods';
import {RequestService} from '../request.service/request.service';
import {ProjectAttachmentModel} from './project-attacment.interface';
import * as moment from 'moment';

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

  formatProjects(projects) {
    return projects.map((data) => {
      if (!data) { return {}; }

      const project = data.project;
      const bids = data.bids;
      const type = this.formatType(project.service && project.service.category && project.service.category.name || '');

      return {
        id: project.id,
        name: project.title,
        type: type,
        budget: this.formatBudget(project.budget),
        posted: project.createdAt ? moment(project.createdAt, 'x') : null,
        highestBid: this.getHighestBid(bids).amount,
        proposals: bids.length,
        paidOptions: this.formatPaidOptions(project.paidOptions),
      };
    });
  }

  formatPaidOptions(paidOptions = []) {
    return paidOptions.map((paidOption) => {
      return {
        title: paidOption.title.split(' ')[0],
        id: paidOption.id,
        color: this.getPaidOptionColor(paidOption.title)
      }
    });
  }

  getHighestBid(bids) {
    return bids.reduce((maxBid, bid) => maxBid.amount < bid.amount ? bid : maxBid, { amount: 0 });
  }

  getPaidOptionColor(title = '') {
    switch (title.toLowerCase()) {
      case 'featured project': return '#3ebcc7';
      case 'urgent project': return '#ef5350';
      case 'private project': return '#969696';
      default: return '#ccc';
    }
  }

  formatType(type) {
    return type.replace(/\s?\(.+\)/, '');
  }

  formatBudget(budget) {
    if (!budget) { return {}; }
    return `$${budget.min}-$${budget.max}`;
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
