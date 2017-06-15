import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProjectService} from "../../../services/project.service/project.service";

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
    private _route: ActivatedRoute,
    private projectService: ProjectService
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
      const category = this.projectService.formatType(el.category.name);
      if (this.abilities.industries.indexOf(category) === -1) {
        this.abilities.industries.push(category);
      }
    });
  }

}
