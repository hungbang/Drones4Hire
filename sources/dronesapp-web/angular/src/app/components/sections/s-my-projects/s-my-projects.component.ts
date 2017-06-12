import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

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

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.currentPage = parseInt(this.route.snapshot.params['page'], 10);

    this.route.params.subscribe(() => {
      this.projects = this.route.snapshot.data['projects'];
    });
  }

  changePage(offset) {
    this.currentPage += offset;

    this.router.navigate(['/my-projects/bidding', this.currentPage]);
  }
}
