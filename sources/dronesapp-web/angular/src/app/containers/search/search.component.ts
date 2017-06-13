import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CategoryModel} from "../../services/common.service/category.interface";
import {deleteNullOrNaN} from "../../shared/common/common-methods";
import {ProjectModel} from "../../services/project.service/project.interface";

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {

  categories: CategoryModel[];
  budgets: any[];
  projects: ProjectModel[];

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const services = this.route.snapshot.data['services'] || [];
    const budgets = this.route.snapshot.data['budgets'] || [];
    const projects = this.route.snapshot.data['projects'] || [];

    this.categories = services
      .map((item) => item.category)
      .filter((item, index, self) =>
        self.findIndex((category) => item.id === category.id) === index);
    this.budgets = budgets.map((item) => {
      return {
        text: `${item.title} ($${item.min} - $${item.max})`,
        value: item.id
      };
    });
    this.projects = projects;

    console.log(this.categories);
  }

  send(sendObj) {
    deleteNullOrNaN(sendObj, 'budget');
    deleteNullOrNaN(sendObj, 'category');
    deleteNullOrNaN(sendObj, 'zipCode');

    console.log(sendObj);
  }

}
