import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class MyProjectsComponent implements OnInit {

  public tabs = [
    { link: 'bidding', text: 'Open for bidding', visibility: true, icon: 'box-search' },
    { link: 'progress', text: 'Work in progress', visibility: true, icon: 'box-settings' },
    { link: 'past', text: 'Past projects', visibility: true, icon: 'box-download' },
  ];

  constructor() { }

  ngOnInit() {
  }

}
