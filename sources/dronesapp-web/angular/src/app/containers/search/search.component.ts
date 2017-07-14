import {Component, EventEmitter, OnInit, ViewEncapsulation} from '@angular/core';
import {} from '@types/googlemaps';
import {ActivatedRoute, NavigationEnd, Router} from '@angular/router';

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';
import {ProjectService} from '../../services/project.service/project.service';
import {extend, deleteNullOrNaN} from '../../shared/common/common-methods';
import {UnSubscribeDirective} from '../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent extends UnSubscribeDirective implements OnInit {
  pilotLocation: LocationModel;
  mapProjects: any[] = [];
  coords: any = null;
  boundsChanges$: EventEmitter<any> = new EventEmitter();

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
  ) {
    super();
  }

  ngOnInit() {
    this.pilotLocation = this.accountService.account.location;
  }

  boundsChange(bounds) {
    const topLeft = bounds.getNorthEast();
    const bottomRight = bounds.getSouthWest();
    this.coords = {
      coordsTopLeft: {
        latitude: topLeft.lat(),
        longitude: topLeft.lng()
      },
      coordsBottomRight: {
        latitude: bottomRight.lat(),
        longitude: bottomRight.lng()
      }
    };

    this.boundsChanges$.emit(this.coords);
  }

  initEvent() {
    this.boundsChanges$
      .takeUntil(this.ngUnSubscribe)
      .debounceTime(150)
      .switchMap(
        (res: any) => {
          const searchObj = {
            budgetId: this.budgetId,
            // postcode: this.postcode,
            serviceCategoryId: this.serviceCategoryId,
            topLeftCoordinates: res.coordsTopLeft,
            bottomRightCoordinates: res.coordsBottomRight,
            statuses: ['NEW']
          };

          deleteNullOrNaN(searchObj, 'serviceCategoryId');
          deleteNullOrNaN(searchObj, 'budgetId');
          // deleteNullOrNaN(searchObj, 'postcode');

          return this.projectService.getProjectsOnMap(searchObj)
        }
      )
      .subscribe(
        (res: any) => {
          this.handleSearch(res);
        },
        err => {
          console.log(err);
        }
      );

    // Subscribe to search changes on page
    this.router.events
      .takeUntil(this.ngUnSubscribe)
      .filter((route) => route instanceof NavigationEnd)
      .subscribe(
        () => {
          this.serviceCategoryId = parseInt(this.route.snapshot.queryParams['serviceCategoryId'], 10);
          this.budgetId = parseInt(this.route.snapshot.queryParams['budgetId'], 10);
          // this.postcode = parseInt(this.route.snapshot.queryParams['postcode'], 10);

          if (this.coords) {
            this.boundsChanges$.emit(this.coords);
          }
        }
      );
  }

  private handleSearch(res) {
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
  }
}
