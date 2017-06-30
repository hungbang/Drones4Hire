import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {LocationModel} from '../../services/common.service/location.interface';
import {AccountService} from '../../services/account.service/account.service';

@Component({
  selector: 'search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class SearchComponent implements OnInit {
  pilotLocation: LocationModel;

  constructor(
    private accountService: AccountService
  ) { }

  ngOnInit() {
    this.pilotLocation = this.accountService.account.location;
  }
}
