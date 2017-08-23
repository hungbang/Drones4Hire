import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {ProjectService} from '../../../services/project.service/project.service';
import {deleteNullOrNaN} from '../../../shared/common/common-methods';
import {CategoryModel} from '../../../services/common.service/category.interface';
import {UnSubscribeDirective} from '../../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 's-search-projects',
  templateUrl: './s-search-projects.component.html',
  styleUrls: ['./s-search-projects.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SSearchProjectsComponent extends UnSubscribeDirective implements OnInit {
  public categories: CategoryModel[];
  public budgets: any[];

  public category;
  public budget;
  public postcode = '';
  public range = '';

  public projects;
  public minPage = 1;
  public maxPage = 1;
  public currentPage = 1;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService
  ) {
    super();
  }

  ngOnInit() {
    const services = this.route.parent.snapshot.data['services'] || [];
    this.budgets = this.route.parent.snapshot.data['budgets'] || [];

    this.categories = services
      .map((item) => item.category)
      .filter((item, index, self) =>
        self.findIndex((category) => item.id === category.id) === index)
      .map((item) =>
        Object.assign({}, item, {
          name: item.name
        }))
      .sort((objA, objB) => {
        return objA.order < objB.order ? -1 : 1
      });

    this.route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(() => {
        const res = this.route.snapshot.data['projects'];
        const projects = res && res.results;
        const currentPage = Number(this.route.snapshot.params['page']);

        if ((!projects || !projects.length) && !isNaN(currentPage) && currentPage > 1 || isNaN(currentPage)) {
          this.router.navigate(['/search', 1], { queryParams: this.route.snapshot.queryParams });
          return;
        }

        this.update(res, projects);

        this.currentPage = currentPage;
        this.budget = this.route.snapshot.queryParams['budgetId'] || -1;
        this.category = this.route.snapshot.queryParams['serviceCategoryId'] || -1;
        this.postcode = this.route.snapshot.queryParams['postcode'] || '';
        this.range = this.route.snapshot.queryParams['range'] || '';
      });
  }

  changePage(page) {
    this.currentPage = page;

    this.router.navigate(['/search', this.currentPage], { queryParams: this.route.snapshot.queryParams });
  }

  update(res, projects) {
    this.projects = this.projectService.formatProjects(projects);
    this.maxPage = Math.ceil(res.totalResults / this.projectService.limitProjectsToShow);
  }

  send(sendObj) {
    deleteNullOrNaN(sendObj, 'budgetId');
    deleteNullOrNaN(sendObj, 'serviceCategoryId');
    deleteNullOrNaN(sendObj, 'postcode');
    deleteNullOrNaN(sendObj, 'range');

    this.router.navigate(['/search', 1], { queryParams: sendObj })
      .then(() => {
        const res = this.route.snapshot.data['projects'];
        const projects = res.results;

        this.update(res, projects);

        this.currentPage = 0;
        setTimeout(() => {
          this.currentPage = 1;
        });
      });
  }
}
