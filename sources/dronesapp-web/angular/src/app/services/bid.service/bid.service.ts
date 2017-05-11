import { Injectable } from '@angular/core';

@Injectable()
export class BidService {
  local = {
    bids: [
      {
        id: 1,
        name: 'Pilot_Test',
        img: '',
        propolsal: 'I can do this job for $750 flat. Local',
        amount: 750.00,
        age: 0
      },
      {
        id: 2,
        name: 'Pilot_Test',
        img: '',
        propolsal: 'I can do this job for $750 flat. Local',
        amount: 750.00,
        age: 0
      },
      {
        id: 3,
        name: 'Pilot_Test',
        img: '',
        propolsal: 'I can do this job for $750 flat. Local',
        amount: 750.00,
        age: 0
      }
    ]
  };

  isAccepted: boolean = false;
  constructor() {
  }

  get bids() {
    return this.local.bids;
  }
}
