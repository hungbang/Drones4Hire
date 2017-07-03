import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {} from '@types/googlemaps';

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';
import {ProjectService} from '../../services/project.service/project.service';
import {Subject} from "rxjs/Subject";

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {
  pilotLocation: LocationModel;
  mapProjects: any[] = [];

  boundsChanges = new Subject();

  constructor(
    private accountService: AccountService,
    private projectService: ProjectService
  ) { }

  ngOnInit() {
    this.pilotLocation = this.accountService.account.location;
  }

  boundsChange(bounds) {
    const topLeft = bounds.getNorthEast();
    const bottomRight = bounds.getSouthWest();
    const coords = {
      coordsTopLeft: {
        latitude: topLeft.lat(),
        longitude: topLeft.lng()
      },
      coordsBottomRight: {
        latitude: bottomRight.lat(),
        longitude: bottomRight.lng()
      }
    };

    this.boundsChanges.next(coords);
  }

  initEvent() {
    this.boundsChanges
      .debounceTime(250)
      .switchMap(
        (res: any) => this.projectService.getProjectsOnMap(res.coordsTopLeft, res.coordsBottomRight)
      )
      .subscribe(
        (res: any) => {
          this.mapProjects = res.results;
          // console.log(this.mapProjects);
        },
        err => {
          console.log(err);
        }
      );
  }
}
