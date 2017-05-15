import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {PortfolioService} from "../../../services/portfolio.service/portfolio.service";

@Component({
  selector: 't-search',
  templateUrl: './t-search.component.html',
  styleUrls: ['./t-search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class TSearchComponent implements OnInit {

  items: any = [];

  constructor(private portfolioService: PortfolioService) {
    this.items = portfolioService.getItems();
  }

  ngOnInit() {
  }

}
