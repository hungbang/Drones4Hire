import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';

import {PaidOptionModel} from './paid-option.interface';
import {createObservable} from '../../shared/common/common-methods';
import {RequestService} from '../request.service/request.service';
import {ProjectAttachmentModel} from './project-attacment.interface';
import * as moment from 'moment';
import {AccountService} from '../account.service/account.service';
import {TransactionService} from '../transaction.service/transaction.service';
import {ToastrService} from '../toastr.service/toastr.service';

@Injectable()
export class ProjectService {
  paidOptions: PaidOptionModel[] = [];
  paidOptionsClasses = ['_featured', '_urgent', '_sealed', '_private'];

  public projects;

  limitProjectsToShow: number = 10;
  nameFilter: string;

  constructor(
    private _requestService: RequestService,
    private _accountService: AccountService,
    private _transactionService: TransactionService,
    private toastrService: ToastrService
  ) {
  }

  resetLimit() {
    this.limitProjectsToShow = 10;
  }

  formatMyProjects(projects) {
    return projects.map((data) => {
      if (!data) { return {}; }

      const project = data.project;

      return {
        id: project.id,
        name: project.title,
        status: project.status,
        bidCount: data.bidsCount,
        averageBid: data.bidsAvg,
        bidEndDate: this.getBidEndDate(project)
      };
    });
  }

  getBidEndDate(project) {
    return project.awardDate ? moment(project.awardDate, 'x') : moment(project.startDate, 'x');
  }

  formatPilotDashboardProjects(projects) {
    const currentPilotId = this._accountService.account.id;

    return projects.map((data) => {
      const project = data.project;
      const bid = data.bids[0];

      let paymentCreated = this._transactionService.getClientToDroneTransaction(data.transactions);
      paymentCreated = this._transactionService.getLastTransaction(paymentCreated);

      let paymentReleased = this._transactionService.getDroneToPilotTransaction(data.transactions);
      paymentReleased = this._transactionService.getLastTransaction(paymentReleased);

      return {
        id: project.id,
        name: project.title,
        bidPlaced: bid.createdAt,
        bidId: bid.id,
        awardedDate: this.getAwardedDate(data),
        paymentCreated: paymentCreated && paymentCreated.createdAt,
        paymentReleased: paymentReleased && paymentReleased.createdAt,
        status: project.status,
        amountPaid: bid.amount,
        pilotId: data.pilot && data.pilot.id,
        currentPilotId,
        attachments: (project.status === 'IN_PROGRESS' || project.status === 'COMPLETED') ? !!project.attachments.filter((project) => project.type === 'PROJECT_RESULT').length : false
      };
    });
  }

  formatClientDashboardProjects(projects) {
    return projects.map((data) => {
      const project = data.project;

      const pilotAttachments = project.attachments.filter((project) => project.type === 'PROJECT_RESULT');

      const paymentCreated = this._transactionService.getClientToDroneTransaction(data.transactions);
      const paymentReleased = this._transactionService.getDroneToPilotTransaction(data.transactions);

      return {
        id: project.id,
        name: project.title,
        bidPlaced: this.getBidPlaced(data),
        awardedDate: this.getAwardedDate(data),
        paymentCreated: paymentCreated && paymentCreated.createdAt,
        paymentReleased: paymentReleased && paymentReleased.createdAt,
        bidsCount: data.bidsCount,
        pilotId: data.pilot && data.pilot.id || null,

        attachmentLength: pilotAttachments.length,
        status: project.status,
        bidId: this.getBidId(data)
      };
    });
  }

  getAwardedDate(data) {
    if (!data.pilot) {
      return null;
    }

    return data.project.awardDate;
  }

  getBidId(data) {
    const bid = this.getBidFromPilot(data.pilot, data.bids);

    return bid ? bid.id : null;
  }

  getBidPlaced(data) {
    const bid = this.getBidFromPilot(data.pilot, data.bids);

    return bid ? bid.createdAt : null;
  }

  getBidFromPilot(pilot, bids) {
    if (pilot) {
      const pilotId = pilot.id;

      return bids.find((bid) => bid.user.id === pilotId);
    }

    return null;
  }

  formatProjects(projects) {
    return projects.map((data) => {
      if (!data) { return {}; }

      const project = data.project;
      const bids = data.bids;
      const type = this.formatType(project.service && project.service.category && project.service.category.name || '');

      return {
        id: project.id,
        distance: this.formatDistance(data.distance),
        name: project.title,
        type: type,
        budget: project.budget ? project.budget.title : '',
        posted: project.createdAt ? moment(project.createdAt, 'x') : null,
        highestBid: this.getHighestBid(bids).amount,
        proposals: bids.length,
        paidOptions: this.formatPaidOptions(project.paidOptions),
      };
    });
  }

  formatDistance(distance) {
    if (!distance) {
      return '';
    }

    return (Math.round(parseFloat(distance) * 100) / 100) + ' Miles';
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
      case 'private project': return '#1440be';
      default: return '#ccc';
    }
  }

  formatType(type) {
    return type.replace(/\s?\(.+\)/, '');
  }

  public formatLocation(location) {
    const out = [];

    if (location.address) {
      out.push(location.address);
    }
    if (location.city) {
      out.push(location.city);
    }
    if (location.state) {
      out.push(location.state.name);
    }
    if (location.country) {
      out.push(location.country.name);
    }
    if (location.postcode) {
      out.push(location.postcode);
    }

    return out.join(', ');
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
        // console.log(res);
        return this.paidOptions;
      });
  }

  public getProjects(search = {}) {
    return this._requestService.fetch('post', '/projects/search', search)
      .map(res => {
        this.projects = res.results;
        return res;
      })
      .catch(
        err => {
          if (err.status === 500) {
            this.toastrService.showError('Internal server error. Please try again later.');
          } else if (err.status === 400) {
            this.toastrService.showError('Please check your data')
          }
          return Observable.throw(err);
        }
      );
  }

  // todo add notification
  public postProjects(data: any) {
    return this._requestService.fetch('post', '/projects', data);
  }

  public updateProject(data: any) {
    return this._requestService.fetch('put', '/projects', data);
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

  public cancelProject(id: number) {
    return this._requestService.fetch('post', `/projects/${id}/cancel`);
  }

  public getProjectsOnMap(data) {
    return this._requestService.fetch('post', '/projects/search/map', data);
  }
}
