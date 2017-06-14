import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import { PilotsService } from '../../../services/pilots.service/pilots.service';
import {AccountService} from '../../../services/account.service/account.service';

@Component({
  selector: 't-dashboard',
  templateUrl: './t-dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./t-dashboard.component.styl']
})
export class TDashboardComponent implements OnInit {
  @Input() projects;

  dashboard: Object;
  constructor(
    public _accountService: AccountService,
    public _pilotsService: PilotsService
  ) { }

  ngOnInit() {
    this.dashboard = this._pilotsService.selectedPilot.dashboard;
  }

  get isClient() {
    return this._accountService.isUserClient();
  }

  get isPilot() {
    return this._accountService.isUserPilot();
  }

  release(project, event) {
    event.preventDefault();
  }
}
