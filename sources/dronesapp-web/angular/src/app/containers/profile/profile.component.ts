import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {UnSubscribeDirective} from '../../shared/un-subscribe/un-subscribe.directive';

@Component({
  selector: 'profile-page',
  templateUrl: './profile.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./profile.component.styl']
})
export class ProfileComponent extends UnSubscribeDirective implements OnInit {
  profile: Object;

  constructor(
    private _route: ActivatedRoute
  ) {
    super();
  }

  ngOnInit() {
    this._route.params
      .takeUntil(this.ngUnSubscribe)
      .subscribe(
        () => {
          this.profile = this._route.snapshot.data['profile'];
        }
      );
  }

}
