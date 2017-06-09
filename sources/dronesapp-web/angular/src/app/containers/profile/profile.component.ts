import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'profile-page',
  templateUrl: './profile.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./profile.component.styl']
})
export class ProfileComponent implements OnInit {
  profile: Object;

  constructor(
    private _route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.profile = this._route.snapshot.data['profile'];
  }

}
