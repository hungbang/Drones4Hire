import { Injectable } from '@angular/core';

interface Similar {
  title: string;
  type: string;
  location: string;
  budget: string;
  date: string;
  src: string;
  highest: string;
  bids: string;
  colors: any[];
}

@Injectable()
export class SimilarService {
  public items: Similar[] = [];

  constructor() {
    this.items = [
      {
        title: 'Humboldt Drone',
        type: 'Real Estate (I.E. Building/Home)',
        location: 'University of North Dakota, United States',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '1000$',
        bids: '2',
        colors: ['#0000ff']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project2.jpg',
        highest: '800$',
        bids: '3',
        colors: ['#f7941d', '#000']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '1050$',
        bids: '5',
        colors: []
      }
    ];
  }
}
