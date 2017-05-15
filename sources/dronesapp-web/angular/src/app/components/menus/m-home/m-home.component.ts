import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";

@Component({
  selector: 'm-home',
  templateUrl: './m-home.component.html',
  styleUrls: ['./m-home.component.styl']
})
export class MHomeComponent implements OnInit {

  isIndex: boolean;

  constructor(
    public _router: Router
  ) { }

  ngOnInit() {
    this._router.events.subscribe((date) => {
      if (date instanceof NavigationEnd) {
        this.isIndex = date.url === '/';
      }
    });
  }

}
