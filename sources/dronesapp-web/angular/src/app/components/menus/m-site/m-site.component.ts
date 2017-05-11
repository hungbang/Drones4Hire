import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'm-site',
  templateUrl: './m-site.component.html',
  styleUrls: ['./m-site.component.styl']
})
export class MSiteComponent implements OnInit {

  isIndex: boolean;
  isProfile: boolean;

  constructor(
    public _router: Router
  ) { }

  ngOnInit() {
    this._router.events.subscribe((date) => {
      if (date instanceof NavigationEnd) {
        this.isIndex = date.url === '/';
        this.isProfile = (date.url !== '/');
      }
    });
  }

}

