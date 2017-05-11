import { Injectable } from '@angular/core';

@Injectable()
export class PilotsService {
  local = {
    selectedPilot: {
      name: 'ToddlesMcGoo',
      img: '../assets/img/photo-profile.jpg',
      status: 'Pilot',
      registrationDate: '12/15/16',
      walletBalance: '$6611.25',
      flightHours: 9999,
      location: 'Kansas, USA',
      rating: 2.5,
      bio: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.',
      dashboard: [
        {
          projectName: 'Test 1',
          clientName: 'Client Name',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: null,
          paymentReleased: null,
          amountPaid: '$500/$1000',
          status: 'Pending'
        },
        {
          projectName: 'Test 2',
          clientName: 'Client Name',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: '6/29/16',
          paymentReleased: '6/29/16',
          amountPaid: null,
          status: 'Completed'
        },
        {
          projectName: 'Test 3',
          clientName: 'Client Name',
          projectBidPlaced: '12/15/16',
          projectAwarded: '6/29/16',
          paymentCreated: null,
          paymentReleased: null,
          amountPaid: null,
          status: 'Accepted'
        },
        {
          projectName: 'Test 4',
          clientName: 'Client Name',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: '6/29/16',
          paymentReleased: null,
          amountPaid: '$500/$1000',
          status: 'Pending'
        },
        {
          projectName: 'Test 5',
          clientName: 'Client Name',
          projectBidPlaced: '12/15/16',
          projectAwarded: null,
          paymentCreated: null,
          paymentReleased: null,
          amountPaid: '$500/$1000',
          status: 'Pending'
        }
      ],
      abilities: {
        services: ['Photo', 'Video'],
        industries: ['Weddings', 'Special Events', 'Promotional', 'TV/Films', 'Constructions'],
        drones: ['Photo', 'Video'],
        cameraTypes: ['Photo', 'Video']
      },
      reviews: [
        {
          author: 'Lorem ipsum',
          date: '9/16/16',
          rating: 2.5,
          text: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.'
        },
        {
          author: 'Lorem ipsum',
          date: '9/16/16',
          rating: 4,
          text: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.'
        },
        {
          author: 'Lorem ipsum',
          date: '9/16/16',
          rating: 5,
          text: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam.'
        }
      ]
    }
  };

  constructor() {
  }

  get selectedPilot() {
    return this.local.selectedPilot;
  }
}
