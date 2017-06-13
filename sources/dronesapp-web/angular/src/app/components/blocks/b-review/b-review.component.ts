import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'b-review',
  templateUrl: './b-review.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-review.component.styl']
})
export class BReviewComponent implements OnInit {
  services: any;
  abilities: any;

  constructor(
    private _route: ActivatedRoute
  ) {
    this.abilities = {
      services: [],
      industries: [],
      drones: [],
      cameraTypes: []
    }
  }

  ngOnInit() {
    this._route.params.subscribe(
      () => {
        this.services = this._route.snapshot.data['services'];
        this.fetchServiceData();
      }
    );
  }

  private fetchServiceData() {
    this.abilities.services = [];
    this.abilities.industries = [];

    this.services.forEach(el => {
      if (this.abilities.services.indexOf(el.name) === -1) {
        this.abilities.services.push(el.name);
      }
      if (this.abilities.industries.indexOf(el.category.name) === -1) {
        this.abilities.industries.push(el.category.name);
      }
    });
  }

}
