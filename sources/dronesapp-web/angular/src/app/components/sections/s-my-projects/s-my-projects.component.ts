import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {BidService} from "../../../services/bid.service/bid.service";

@Component({
  selector: 's-my-projects',
  templateUrl: './s-my-projects.component.html',
  styleUrls: ['./s-my-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SMyProjectsComponent implements OnInit {
  public projects;
  public currentPage = 1;
  public minPage = 1;
  public maxPage = 2;
  public rows;

  public countPerPage: number;
  public title: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private bidService: BidService
  ) { }

  ngOnInit() {
    this.currentPage = parseInt(this.route.snapshot.params['page'], 10);

    this.route.params.subscribe(() => {
      this.projects = this.route.snapshot.data['projects'];
      this.countPerPage = this.bidService.countPerPage;
      this.title = this.route.snapshot.queryParams['title'];
    });
  }

  changePage(offset) {
    this.currentPage += offset;

    this.router.navigate(['/my-projects/bidding', this.currentPage], { queryParams: this.route.snapshot.queryParams });
  }

  search({ title, countPerPage }) {
    this.title = title;
    this.countPerPage = countPerPage;

    const queryParams = {
      title, count: countPerPage
    };

    if (!title) {
      delete queryParams.title;
    }

    this.router.navigate(['/my-projects/bidding', this.currentPage], {
      queryParams
    }).then(() => {
      this.projects = this.route.snapshot.data['projects'];
      this.countPerPage = this.bidService.countPerPage;
      this.title = this.route.snapshot.queryParams['title'];
    })
  }
}
