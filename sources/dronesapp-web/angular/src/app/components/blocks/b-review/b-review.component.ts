import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {ProjectService} from '../../../services/project.service/project.service';
import {UnSubscribeDirective} from '../../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 'b-review',
  templateUrl: './b-review.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./b-review.component.styl']
})
export class BReviewComponent extends UnSubscribeDirective implements OnInit {
  abilities: any;

  constructor(
    private _route: ActivatedRoute,
    private projectService: ProjectService
  ) {
    super();
    this.abilities = {
      services: [],
      industries: [],
      drones: [],
      cameras: []
    }
  }

  ngOnInit() {
    this._route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(
        () => {
          const services = this._route.snapshot.data['services'];
          const equipments = this._route.snapshot.data['equipments'];
          this.abilities = {
            services: [],
            industries: [],
            drones: [],
            cameras: []
          };

          if (services.length) {
            this.fetchServiceData(services);
          }
          if (equipments.length) {
            this.fetchEquipmentsData(equipments);
          }
        }
      );
  }

  private fetchServiceData(services) {
    this.abilities.services = [];
    this.abilities.industries = [];

    services.forEach(el => {
      if (this.abilities.services.indexOf(el.name) === -1) {
        this.abilities.services.push(el.name);
      }
      const category = this.projectService.formatType(el.category.name);
      if (this.abilities.industries.indexOf(category) === -1) {
        this.abilities.industries.push(category);
      }
    });
  }

  private fetchEquipmentsData(equipments) {
    this.abilities.drones = equipments.filter(el => el.type === 'DRONE');
    this.abilities.cameras = equipments.filter(el => el.type === 'CAMERA');
  }

}
