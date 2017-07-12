import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {} from '@types/googlemaps';
import {Subject} from 'rxjs/Subject';
import {ActivatedRoute, Router} from '@angular/router';

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';
import {ProjectService} from '../../services/project.service/project.service';
import {extend, deleteNullOrNaN} from '../../shared/common/common-methods';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit, OnDestroy {
  pilotLocation: LocationModel;
  mapProjects: any[] = [];

  boundsChanges = new Subject();

  serviceCategoryId: number|null = null;
  budgetId: number|null = null;
  // postcode: number|null = null; // TODO: temporary disabled from search. Remove if will not returned back
  range: number|null = null;
  statuses: string[] = ['NEW'];

  constructor(
    private accountService: AccountService,
    private projectService: ProjectService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.pilotLocation = this.accountService.account.location;

    this.router.events.subscribe(
      (item) => {
        this.serviceCategoryId = +this.route.snapshot.queryParams['serviceCategoryId'];
        this.budgetId = +this.route.snapshot.queryParams['budgetId'];
        // this.postcode = +this.route.snapshot.queryParams['postcode'];
        const range = +this.route.snapshot.queryParams['range'];
      }
    );
  }

  ngOnDestroy() {
    this.boundsChanges.unsubscribe();
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
        (res: any) => {
          const searchObj = {
            budgetId: this.budgetId,
            // postcode: this.postcode,
            serviceCategoryId: this.serviceCategoryId,
            range: this.range,
            topLeftCoordinates: res.coordsTopLeft,
            bottomRightCoordinates: res.coordsBottomRight,
            statuses: ['NEW']
          };

          deleteNullOrNaN(searchObj, 'serviceCategoryId');
          deleteNullOrNaN(searchObj, 'budgetId');
          // deleteNullOrNaN(searchObj, 'postcode');
          deleteNullOrNaN(searchObj, 'range');

          return this.projectService.getProjectsOnMap(searchObj)
        }
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
