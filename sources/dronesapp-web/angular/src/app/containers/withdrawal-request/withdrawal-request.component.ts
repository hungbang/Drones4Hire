import { Component, OnInit, ViewEncapsulation } from '@angular/core';

import {AccountService} from '../../services/account.service/account.service';

@Component({
  selector: 'withdrawal-request',
  templateUrl: './withdrawal-request.component.html',
  styleUrls: ['./withdrawal-request.component.styl'],
  encapsulation: ViewEncapsulation.None
})
export class WithdrawalRequestComponent implements OnInit {
  showSuccessText: boolean = false;

  constructor(
    private accountService: AccountService
  ) { }

  ngOnInit() {
    console.log(this.accountService.account);
  }

  get accountBalance() {
    return this.accountService.account.wallet.balance; // TODO: update balance and status on open page when API will be ready
  }

}
