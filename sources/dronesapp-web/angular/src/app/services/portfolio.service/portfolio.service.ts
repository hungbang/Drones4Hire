import { Injectable } from '@angular/core';

interface Portfolio {
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

interface Portfolios extends Array<Portfolio>{}

@Injectable()
export class PortfolioService {
  public items: Portfolios = [];

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
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project2.jpg',
        highest: '1200$',
        bids: '6',
        colors: ['#000', '#959595']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '900$',
        bids: '3',
        colors: []
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project2.jpg',
        highest: '1000$',
        bids: '2',
        colors: ['#0000ff', '#f7941d']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '1200$',
        bids: '5',
        colors: ['#3ebcc7']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project2.jpg',
        highest: '1000$',
        bids: '2',
        colors: ['#000', '#959595', '#3ebcc7']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '1000$',
        bids: '2',
        colors: []
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project2.jpg',
        highest: '1000$',
        bids: '2',
        colors: ['#000', '#959595', '#3ebcc7', '#f7941d']
      },
      {
        title: 'Title',
        type: 'Type',
        location: 'location',
        budget: 'budget',
        date: '19/01/1990',
        src: '../assets/img/project1.jpg',
        highest: '1000$',
        bids: '2',
        colors: []
      }
    ];
  }
  getItems(): any {
    return this.items;
  }

}
