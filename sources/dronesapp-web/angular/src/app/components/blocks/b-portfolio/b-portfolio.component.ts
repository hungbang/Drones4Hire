import {Component, OnInit, ViewEncapsulation, Input} from '@angular/core';

declare const device: any;

@Component({
  selector: 'b-portfolio',
  templateUrl: './b-portfolio.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-portfolio.component.styl']
})
export class BPortfolioComponent implements OnInit {
  @Input() item;

  public toggleClass: boolean = false;
  constructor() {}

  click() {
    if (window.innerWidth < 1025) {
      this.toggleClass = !this.toggleClass;
    }
  }

  ngOnInit() {
  }

}
