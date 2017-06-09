import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {PortfolioService} from '../../../services/portfolio.service/portfolio.service';

@Component({
  selector: 'l-portfolio',
  templateUrl: './l-portfolio.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./l-portfolio.component.styl']
})
export class LPortfolioComponent implements OnInit {

  constructor(
    public portfolioService: PortfolioService
  ) { }

  ngOnInit() { }

}
