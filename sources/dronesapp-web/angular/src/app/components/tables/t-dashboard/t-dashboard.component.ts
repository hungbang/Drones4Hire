import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AppService } from '../../../services/app.service/app.service';
import { PilotsService } from '../../../services/pilots.service/pilots.service';

@Component({
  selector: 't-dashboard',
  templateUrl: './t-dashboard.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./t-dashboard.component.styl']
})
export class TDashboardComponent implements OnInit {
  dashboard: Object;
  constructor(
    public _appService: AppService,
    public _pilotsService: PilotsService
  ) { }

  ngOnInit() {
    this.dashboard = this._pilotsService.selectedPilot.dashboard;
  }

}
