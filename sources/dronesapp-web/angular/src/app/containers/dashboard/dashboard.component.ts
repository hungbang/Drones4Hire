import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { PilotsService } from '../../services/pilots.service/pilots.service';
import { ClientsService } from '../../services/clients.service/clients.service';
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
    public _accountService: AccountService,
    public _pilotsService: PilotsService,
    public _clientsService: ClientsService
  ) { }

  ngOnInit() {
    this.profile = this._accountService.isUserClient() ? this._pilotsService.selectedPilot : this._clientsService.selectedClient; // todo add selected Client
  }

}
