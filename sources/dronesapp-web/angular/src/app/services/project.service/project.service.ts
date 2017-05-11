import { Injectable } from '@angular/core';

@Injectable()
export class ProjectService {
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

  constructor() {
  }

  get myProjects() {
    return this.local.myProjects;
  }
}
