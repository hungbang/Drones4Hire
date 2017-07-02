import {Component, NgZone, OnInit, ViewEncapsulation} from '@angular/core';
import {} from '@types/googlemaps';
import {MapsAPILoader} from '@agm/core'

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';
import {ProjectService} from '../../services/project.service/project.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {
  pilotLocation: LocationModel;
  boundsTicking: boolean = false;
  mapProjects: any[] = [];

  constructor(
    private accountService: AccountService,
    private projectService: ProjectService,
    private ngZone: NgZone
  ) { }

  ngOnInit() {
    this.pilotLocation = this.accountService.account.location;
  }

  boundsChange(bounds) {
    const topLeft = bounds.getNorthEast();
    const bottomRight = bounds.getSouthWest();
    const coordsTopLeft = {
      latitude: topLeft.lat(),
      longitude: topLeft.lng()
    };
    const coordsBottomRight = {
      latitude: bottomRight.lat(),
      longitude: bottomRight.lng()
    };


    if (!this.boundsTicking) {
      this.boundsTicking = true;
      setTimeout(() => {
        this.ngZone.run(() => {
          this.setProjectsOnMap(coordsTopLeft, coordsBottomRight);
        });
        this.boundsTicking = false;
      }, 250)
    }
  }

  private setProjectsOnMap(coordsTopLeft, coordsBottomRight) {
    this.projectService.getProjectsOnMap(coordsTopLeft, coordsBottomRight)
      .subscribe(
        res => {
          console.log(res);
          this.mapProjects = res.results;
          console.log(this.mapProjects);
        },
        err => {
          console.log(err);
        }
      );
  }
}
