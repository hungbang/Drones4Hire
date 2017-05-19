import { Injectable } from '@angular/core';

@Injectable()
export class ClientsService {
  local = {
    selectedClient: {
      name: 'tester99',
      img: './assets/img/photo-profile.jpg',
      status: 'Client',
      registrationDate: '6/30/16',
      walletBalance: '$3157.25',
      rating: 4.5,
      dashboard: [
        {
          projectName: 'Test 1',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: '12/15/16',
          paymentReleased: null
        },
        {
          projectName: 'Test 2',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: null,
          paymentReleased: '6/29/16'
        },
        {
          projectName: 'Test 3',
          projectBidPlaced: '12/15/16',
          projectAwarded: '6/29/16',
          paymentCreated: null,
          paymentReleased: null
        },
        {
          projectName: 'Test 4',
          projectBidPlaced: null,
          projectAwarded: null,
          paymentCreated: '6/29/16',
          paymentReleased: '12/15/16'
        },
        {
          projectName: 'Test 5',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: null,
          paymentReleased: null
        }
      ]
    }
  };
  constructor() { }

  get selectedClient() {
    return this.local.selectedClient;
  }
}
