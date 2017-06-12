import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {

  services: any[];
  budgets: any[];

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const services = this.route.snapshot.data['services'] || [];
    const budgets = this.route.snapshot.data['budgets'] || [];

    this.services = services
      .filter((item, index, self) =>
        self.findIndex((service) => item.name === service.name) === index);
    this.budgets = budgets.map((item) => {
      return {
        text: `${item.title} ($${item.min} - $${item.max})`,
        value: item.id
      };
    });

    console.log(this.budgets);
  }

  send(sendObj) {
    if (isNaN(sendObj.zipCode)) {
      delete sendObj.zipCode;
    }
    if (isNaN(sendObj.budget) || !sendObj.budget) {
      delete sendObj.budget;
    }


    console.log(sendObj);
  }

}
