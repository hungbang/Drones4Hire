import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

import {AccountService} from '../../services/account.service/account.service';

@Component({
  selector: 'dashboard-page',
  templateUrl: './dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./dashboard.component.styl']
})
export class DashboardComponent implements OnInit {
  profile: Object;
  constructor(
    private route: ActivatedRoute,
    public _accountService: AccountService,
  ) { }

  ngOnInit() {
    this.profile = this.route.snapshot.data['profile']
  }

}
