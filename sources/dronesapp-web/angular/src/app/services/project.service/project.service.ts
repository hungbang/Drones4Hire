import {Injectable} from '@angular/core';
import {NormalizedServiceModel} from '../common.service/services.interface';
import {PaidOptionModel} from './paid-option.interface';
import {createObservable} from '../../shared/common/common-methods';
import {RequestService} from '../request.service/request.service';

@Injectable()
export class ProjectService {
  paidOptions: PaidOptionModel[] = [];
  paidOptionsClasses = ['_featured', '_urgent', '_sealed', '_private'];

  public projects;

  local = {
    myProjects: [
      {
        name: 'Privat Test',
        bids: 1,
        avgBids: 250,
        bidEndDate: '2016-11-12',
        isInProcess: false
      },
      {
        name: 'EXTRAS TEST',
        bids: 2,
        avgBids: 3000,
        bidEndDate: '2016-10-01',
        isInProcess: true
      },
      {
        name: 'Privat Test',
        bids: 1,
        avgBids: 250,
        bidEndDate: '2016-11-12',
        isInProcess: true
      },
      {
        name: 'EXTRAS TEST',
        bids: 2,
        avgBids: 3000,
        bidEndDate: '2016-10-01',
        isInProcess: false
      },
      {
        name: 'Privat Test',
        bids: 1,
        avgBids: 250,
        bidEndDate: '2016-11-12',
        isInProcess: false
      },
      {
        name: 'EXTRAS TEST',
        bids: 2,
        avgBids: 3000,
        bidEndDate: '2016-10-01',
        isInProcess: true
      },
      {
        name: 'Privat Test',
        bids: 1,
        avgBids: 250,
        bidEndDate: '2016-11-12',
        isInProcess: true
      },
      {
        name: 'EXTRAS TEST',
        bids: 2,
        avgBids: 3000,
        bidEndDate: '2016-10-01',
        isInProcess: false
      },
      {
        name: 'Privat Test',
        bids: 1,
        avgBids: 250,
        bidEndDate: '2016-11-12',
        isInProcess: false
      },
      {
        name: 'EXTRAS TEST',
        bids: 2,
        avgBids: 3000,
        bidEndDate: '2016-10-01',
        isInProcess: true
      }
    ]
  };
  project = {
    _id: '58fdc70bbeba55ec3a98f16b',
    name: 'Awesome project',
    description: 'Velit ut occaecat pariatur exercitation dolor officia sint laborum. Dolor irure id elit elit ut mollit tempor ex. Proident commodo sit occaecat reprehenderit culpa tempor excepteur aliqua labore est laboris consectetur velit amet. Ad ad dolor culpa laboris culpa ea. Nulla amet commodo ut nisi adipisicing sit pariatur sunt dolore incididunt aliquip aute.',
    details: {
      postedBy: 'Chack Norries',
      type: 'Wedding',
      location: 'Kansas City, MO',
      budget: '$350-$1000',
      postDate: 1461314135713,
      jobDate: 1464805674002
    }
  };

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
        return this.projects;
      });
  }

  // todo add notification
  public postProjects(data: any) {
    return this._requestService.fetch('post', '/projects', data);
  }

  public getProject(id: string|number) {
    return this._requestService.fetch('get', `/projects/${id}`);
  }
}
