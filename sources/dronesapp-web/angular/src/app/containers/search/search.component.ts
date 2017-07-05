import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {} from '@types/googlemaps';
import {Subject} from 'rxjs/Subject';

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';
import {ProjectService} from '../../services/project.service/project.service';
import {extend} from '../../shared/common/common-methods';

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
      .debounceTime(150)
      .switchMap(
        (res: any) => this.projectService.getProjectsOnMap(res.coordsTopLeft, res.coordsBottomRight)
      )
      .subscribe(
        (res: any) => {
          const oldProjects = extend([], this.mapProjects);
          const results = res.results;

          if (!results.length && this.mapProjects.length) {
            this.mapProjects = []; // mutate: clean array
          } else if (results.length && this.mapProjects.length) {
            oldProjects.forEach( // mutate: remove not in bounds projects
              (el, i) => {
                if (!results.some(item => item.id === el.id)) {
                  this.mapProjects.splice(i, 1);
                }
              }
            );
            results.forEach( // mutate: add new projects of current bounds
              (el) => {
                if (!this.mapProjects.some(item => item.id === el.id)) {
                  this.mapProjects.push(el);
                }
              }
            );
          } else if (results.length && !this.mapProjects.length) {
            this.mapProjects = results; // we can change empty array
          }
          // console.log(this.mapProjects);
        },
        err => {
          console.log(err);
        }
      );
  }
}
